package com.nissan.training.corejava.project.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.enums.BookingStatus;
import com.nissan.training.corejava.project.enums.Validators;
import com.nissan.training.corejava.project.interfaces.IInputInterface;
import com.nissan.training.corejava.project.models.BookingModel;
import com.nissan.training.corejava.project.models.TicketsModel;
import com.nissan.training.corejava.project.services.BookingService;
import com.nissan.training.corejava.project.services.FlightService;
import com.nissan.training.corejava.project.services.PaymentGatewayService;
import com.nissan.training.corejava.project.utils.MD5;
import com.nissan.training.corejava.project.utils.ReadInput;
import com.nissan.training.corejava.project.utils.Utility;
import com.nissan.traning.corejava.project.exception.CardNotFoundException;

/**
 * The class implements all methods necessary for booking 
 * @author NDH00159
 *
 */
public class BookingController implements IInputInterface{

	BookingModel bookingModel = null;
	BookingService bookingService = null;
	ReadInput readInput = null;
	ArrayList<TicketsModel> ticketsModelsList = null;
	
	private int customerID;
	private int numberOfTickets;
	private int totalCost;
	public BookingController(int custtomerID) {
		bookingModel = new BookingModel();
		bookingService = new BookingService();
		readInput = new ReadInput();
		this.customerID = custtomerID;
		ticketsModelsList = new ArrayList<>();
	}

	/**
	 * Show all bookings for a customer
	 * @param customerId
	 * @throws SQLException
	 */
	public void showAllBooking(int customerId) throws SQLException {
		
		ArrayList<BookingModel> bookings = bookingService.getAllBookingsByCustomer(customerId);
		Utility.printLabelsBookings();
		for(BookingModel booking:bookings) {
			System.out.print(" "+ booking.getBookingID() + "  ");
			System.out.print(" "+booking.getTicketID()+" ");
			System.out.print(booking.getBookingDate() + " ");
			System.out.print(booking.getCustomerName()+ "  ");
			System.out.print(booking.getCustomerContactNumber()+" ");
			
			System.out.print(booking.getFlightNumber()+" ");
			System.out.print(booking.getFlightArrival()+" ");
			System.out.print(booking.getFlightDeparture()+" ");
			System.out.print(booking.getPassengerName() + "  ");
			System.out.print(booking.getPassengerEmail() + "  ");
			System.out.print(booking.getPassengerDOB() + "  ");
			System.out.println();
		}
	}


	/**
	 * books a flights
	 * saves individual ticket model
	 * @param customerID
	 * @return
	 * @throws SQLException
	 */
	public int bookFlight(int customerID) throws SQLException {
		
		int bookingID = BookingService.saveBooking(customerID);
		for(TicketsModel ticketModel:ticketsModelsList) {
			ticketModel.setBookingID(bookingID);
			ticketModel.save();
		}
		
		FlightService.bookSeats(numberOfTickets,ticketsModelsList.get(0).getFlightID());
		return bookingID;
	}

	@Override
	public void takeInput(){
		
				
		String flightNumber = null;
		while(true) {
			System.out.print("\nEnter Flight Number which you want to book : ");
			flightNumber = readInput.getInputString(Validators.NO_VALIDATE);
			if(FlightService.checkValidFlight(flightNumber)) {
				break;
			}else {
				System.out.print("\nPlease enter a valid flight Number");
			}
		}
		
		ResultSet rs = null;
		try {
			rs = FlightService.getFlightDetails(flightNumber);
			rs.next();

			System.out.print("How many Tickets do you want to book :  ");
			numberOfTickets = readInput.getInputInteger(Validators.NO_VALIDATE);
			
			//get cost of the each ticket and calculates total amount
			totalCost = numberOfTickets * rs.getInt(3);
			
			//checking to see if there are available seats
			if(numberOfTickets > rs.getInt(2)){
				System.out.println("Number of tickets exceeding the number of available seats!");
				return;
			}
				
			for(int i=0;i<numberOfTickets;i++) {
				System.out.println("Ticket "+ String.valueOf(i+1));
				
				TicketsModel ticketsModel = new TicketsModel();
				ticketsModel.setCustomerID(this.customerID);
				
			
				try {
					ticketsModel.setFlightID(rs.getInt(1));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ticketsModel.setBookingTime(LocalDateTime.now().toString());
				ticketsModel.setBookingStatus(BookingStatus.ACTIVE);
				
				System.out.print("Enter passenger name : ");
				ticketsModel.setPassengerName(readInput.getInputString(Validators.NO_VALIDATE));
				
				System.out.print("Enter passenger DOB (DD/MM/YYYY) format : ");
				ticketsModel.setPassengerDOB(readInput.getInputString(Validators.DOB));
				
				System.out.print("Enter passenger Email : ");
				ticketsModel.setPassengerEmail(readInput.getInputString(Validators.EMAIL));
				
				ticketsModelsList.add(ticketsModel);
			}
			
			//Printing the total bill
			System.out.print("\nYour total bill  is Rs "+totalCost);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * generate pnr
	 * @param bookingID
	 * @return
	 */
	public String generatePNR(int bookingID) {
		
		String query = "select b.booking_id, b.cust_id, t.booking_time, f.flight_number from Booking as b inner join Ticket as t on b.booking_id=t.ticket_id " + 
					"inner join Flight as f on f.flight_id = t.flight_id where b.booking_id="+bookingID;
		
		DatabaseWrapper db = new DatabaseWrapper();
		String pnr = null;
		try {
			ResultSet rs = db.executeSqlQuerySelect(query);
			while(rs.next()) {
				String pnrFormat = "%s-%s-%s-%s";
				pnr = String.format(pnrFormat, rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pnr;
	}

	/**
	 * takes card details and makes payment
	 * @return
	 */
	public boolean makePayment() {
		PaymentGatewayService paymentGatewayService = new PaymentGatewayService();
		
		while(true) {
			System.out.print("\nEnter Card Number : ");
			String cardNumber = readInput.getInputString(Validators.CARD_NUMBER);
			System.out.print("Enter Pin Code : ");
			String pin  = readInput.getInputString(Validators.PIN);
			
			try {
				paymentGatewayService.verifyCardDetails(cardNumber, MD5.getHash(pin));
				System.out.print("\nPayment of "+totalCost + " succesfully made. Thank you");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (CardNotFoundException e) {
				System.err.println("***Error. Card not found");
				e.printStackTrace();
			}
			
			System.out.print("\nDo you want to try again ? [1=YES , 0 = NO]");
			int res = readInput.getInputInteger(Validators.NO_VALIDATE);
			if(res==0)
				return false;
		}
	}

	public void modifyBooking(int bookingID) {
		
		
	}


}

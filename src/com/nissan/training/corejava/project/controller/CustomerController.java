package com.nissan.training.corejava.project.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.nissan.training.corejava.project.enums.Validators;
import com.nissan.training.corejava.project.services.CustomerService;
import com.nissan.training.corejava.project.utils.ClearConsole;
import com.nissan.training.corejava.project.utils.ReadInput;

/**
 * Responsible for managing the page/console after customer logs in
 * @author NDH00159
 *
 */
public class CustomerController {

	private int customerId;
	Scanner in = null;
	ReadInput readInput = null;
	
	public CustomerController(int custId) {
		this.customerId = custId;
		in = new Scanner(System.in);
		readInput = new ReadInput();
	}
	
	public void start() {
		//clear screen
		ClearConsole.clearConsole();
		System.out.print("\n");
		
		//get customer Name from customer Id from Customer Service class
		String customerName = CustomerService.getCustomerName(this.customerId);
		
		System.out.print("\n*Logged in succesfully \n");
		System.out.println("Welcome "+customerName+"\n");
		
		//creating bookingCOntroller object responsible for dealing with booking model and booking database
		BookingController bookingController = new BookingController(this.customerId);
		
		while(true) {
			
			System.out.print("\n\n\n");
			System.out.print("\n>Enter 1 to See all my Bookings");
			System.out.print("\n>Enter 2 to Book a flight");
			System.out.print("\n>Enter 3 to Modify a Booking");
			System.out.print("\n>Enter 4 to Update your account ");
			System.out.print("\n>Enter 5 to exit ");
			
			System.out.print("\nYour choice :  ");
			
			int option = in.nextInt();
			int bookingID;
			
			switch(option) {
			case 1:
				try {
					//show all booking by customer with customerID
					bookingController.showAllBooking(customerId);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case 3:
				System.out.print("\nYour booked flights are ");
				try {
					bookingController.showAllBooking(customerId);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				System.out.print("\nEnter Booking ID : ");
				bookingID = readInput.getInputInteger(Validators.NO_VALIDATE);
				bookingController.modifyBooking(bookingID);
				break;
			case 2:
				try {
					//show all available flights
					FlightController.showAllAvailableFLights();
					
					//take input details for flight
					bookingController.takeInput();
					
					//make payment
					boolean successPayment = bookingController.makePayment();
					if(!successPayment)
						break;
					
					//if payment done then book flight
					bookingID = bookingController.bookFlight(this.customerId);
					
					//generate pnr 
					String pnr = bookingController.generatePNR(bookingID);
					System.out.print("\nYou flight has been booked");
					System.out.print("\nPNR is "+pnr);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				break;
			case 5:
				System.out.print("\nCLosing.. Thank you");
				System.exit(0);
			default:
				System.out.println("Wrong Input");
			}
			
			
		}
		
		
	}

}

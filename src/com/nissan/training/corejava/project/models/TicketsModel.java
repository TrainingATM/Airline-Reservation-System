package com.nissan.training.corejava.project.models;

import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.enums.BookingStatus;
import com.nissan.training.corejava.project.interfaces.IModel;

public class TicketsModel implements IModel{

	private int ticketID;
	private int flightID;
	private int customerID;
	private String bookingTime;
	private String passengerName;
	private String passengerDOB;
	private String passengerEmail;
	private BookingStatus bookingStatus;
	private int bookingID;
	
	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public int getFlightID() {
		return flightID;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerDOB() {
		return passengerDOB;
	}

	public void setPassengerDOB(String passengerDOB) {
		this.passengerDOB = passengerDOB;
	}

	public String getPassengerEmail() {
		return passengerEmail;
	}

	public void setPassengerEmail(String passengerEmail) {
		this.passengerEmail = passengerEmail;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public TicketsModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save() throws SQLException {
		
		String q = "insert into Ticket values (NULL, %s,%s,'%s','%s','%s','%s','%s',%s)";
		String query = String.format(q, this.flightID,this.customerID,this.bookingTime,
								this.passengerName,
								this.passengerDOB,
								this.passengerEmail,
								this.bookingStatus,
								this.bookingID
						);
		DatabaseWrapper db = new DatabaseWrapper();
		db.executeSqlQueryInsert(query);
				
		
	}

}

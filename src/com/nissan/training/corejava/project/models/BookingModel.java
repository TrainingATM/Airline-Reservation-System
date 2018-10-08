package com.nissan.training.corejava.project.models;

import java.sql.SQLException;

import com.nissan.training.corejava.project.enums.BookingStatus;
import com.nissan.training.corejava.project.interfaces.IModel;

public class BookingModel implements IModel{

	


	private int bookingID;
	private int ticketID;
	private String customerName;
	private String customerContactNumber;
	private String flightNumber;
	private String flightDeparture;
	private String flightArrival;
	private String bookingDate;
	private String passengerName;
	private String passengerDOB;
	private String passengerEmail;
	private BookingStatus bookingStatus;
	
	public BookingModel() {
		// TODO Auto-generated constructor stub
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}
	
	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightDeparture() {
		return flightDeparture;
	}

	public void setFlightDeparture(String flightDeparture) {
		this.flightDeparture = flightDeparture;
	}

	public String getFlightArrival() {
		return flightArrival;
	}

	public void setFlightArrival(String flightArrival) {
		this.flightArrival = flightArrival;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
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

	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}

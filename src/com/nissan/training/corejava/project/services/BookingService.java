package com.nissan.training.corejava.project.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.enums.BookingStatus;
import com.nissan.training.corejava.project.models.BookingModel;

public class BookingService {

	DatabaseWrapper db = null;
	public BookingService() {
		db = new DatabaseWrapper();
	}

	/**
	 * utility for getting all Bookings by customer from db
	 * @param customerId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BookingModel> getAllBookingsByCustomer(int customerId) throws SQLException {
		
		
		String query = "select b.booking_id,t.ticket_id,t.booking_time,t.passenger_name,t.passenger_dob,t.passenger_email,"+
					" t.ticket_status,c.cust_name,c.cust_contactNumber,f.flight_number,"+
					" f.flight_arrival,f.flight_departure "+
					" from Booking as b inner join Ticket as t on b.booking_id = t.booking_id "+
					" inner join Customer as c on b.cust_id = c.cust_id "+
					" inner join Flight as f on t.flight_id = f.flight_id "+
					" where b.cust_id="+customerId;

		ResultSet rs = db.executeSqlQuerySelect(query);
		
		ArrayList<BookingModel> arr = new ArrayList<>();
		while(rs.next()) {
			BookingModel bookingModel = new BookingModel();
			bookingModel.setBookingID(Integer.parseInt(rs.getString(1)));
			bookingModel.setTicketID(Integer.parseInt(rs.getString(2)));
			bookingModel.setBookingDate(rs.getString(3));
			bookingModel.setPassengerName(rs.getString(4));
			bookingModel.setPassengerDOB(rs.getString(5));
			bookingModel.setPassengerEmail(rs.getString(6));
			bookingModel.setBookingStatus(BookingStatus.valueOf(rs.getString(7)));
			bookingModel.setCustomerName(rs.getString(8));
			bookingModel.setCustomerContactNumber(rs.getString(9));
			bookingModel.setFlightNumber(rs.getString(10));
			bookingModel.setFlightArrival(rs.getString(11));
			bookingModel.setFlightDeparture(rs.getString(12));
			
			arr.add(bookingModel);
		}
		return arr;
	}

	/**
	 * utlity for saving a booking to db
	 * @param customerID
	 * @return
	 * @throws SQLException
	 */
	public static int saveBooking(int customerID) throws SQLException {
		String q = "insert into Booking values(NULL,%s)";
		String query = String.format(q, customerID);
		DatabaseWrapper db = new DatabaseWrapper();
		db.executeSqlQueryInsert(query);
		query = "SELECT LAST_INSERT_ID()";
		ResultSet rs = db.executeSqlQuerySelect(query);
		rs.next();
		return rs.getInt(1);
		
	}
	
	

}

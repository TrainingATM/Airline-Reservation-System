package com.nissan.training.corejava.project.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nissan.training.corejava.project.doa.DatabaseUtil;
import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.enums.FlightStatus;
import com.nissan.training.corejava.project.models.FlightModel;

public class FlightService {

	//wrapper for sql queries
	DatabaseWrapper db = new DatabaseWrapper();
	public FlightService() {
		db = new DatabaseWrapper();
	}
	
	//R operation in CRUD
	/**
	 * returns all flights
	 * @param time
	 * @return FlightModel
	 * @throws SQLException 
	 */
	public ArrayList<FlightModel> getAllFlights() throws SQLException {
		
		String q = "select * from Flight";
		ResultSet rs = db.executeSqlQuerySelect(q);
		
		ArrayList<FlightModel> arr = new ArrayList<>();
		while(rs.next()) {
			FlightModel flightModel = new FlightModel();
			flightModel.setFlightID(rs.getInt(1));
			flightModel.setFlightArrival(rs.getString(2));
			flightModel.setFlightDeparture(rs.getString(3));
			flightModel.setFlightArrivalTime(rs.getString(4));
			flightModel.setFlightDepartureTime(rs.getString(5));
			flightModel.setFlightCompany(rs.getString(6));
			flightModel.setFlightNumber(rs.getString(7));
			flightModel.setFlightNumberOfSeats(rs.getInt(8));
			flightModel.setFlightStatus(FlightStatus.valueOf(rs.getString(9)));
			flightModel.setFlightType(rs.getString(10));
			flightModel.setFlightCost(rs.getInt(11));
			arr.add(flightModel);
			
		}
		return arr;
	}
	
	/**
	 * get all flights available flights
	 * @return {@link ArrayList}
	 * @throws SQLException
	 */
public ArrayList<FlightModel> getAllAvailableFlights() throws SQLException {
		
		String q = "select * from Flight where flight_status='DELAYED' or flight_status='ON_TIME'";
		
		ResultSet rs = db.executeSqlQuerySelect(q);
		
		ArrayList<FlightModel> arr = new ArrayList<>();
		while(rs.next()) {
			FlightModel flightModel = new FlightModel();
			flightModel.setFlightID(rs.getInt(1));
			flightModel.setFlightArrival(rs.getString(2));
			flightModel.setFlightDeparture(rs.getString(3));
			flightModel.setFlightArrivalTime(rs.getString(4));
			flightModel.setFlightDepartureTime(rs.getString(5));
			flightModel.setFlightCompany(rs.getString(6));
			flightModel.setFlightNumber(rs.getString(7));
			flightModel.setFlightNumberOfSeats(rs.getInt(8));
			flightModel.setFlightStatus(FlightStatus.valueOf(rs.getString(9)));
			flightModel.setFlightType(rs.getString(10));
			flightModel.setFlightCost(rs.getInt(11));
			arr.add(flightModel);
			
		}
		return arr;
	}
	
	
	/**
	 * cancel a flight with a flight ID
	 * @param flightID
	 * @throws SQLException
	 */
	public void canecelFlight(int flightID) throws SQLException {
		String query = "Update Flight set flight_status='CANCELLED' where flight_id="+flightID;
		db.executeSqlQueryUpdate(query);
	}


	/**
	 * change flight timings
	 * @param flightID
	 * @param newDepartureTime
	 * @param newArrivalTime
	 * @throws SQLException
	 */
	public void changeFLightTimings(int flightID, String newDepartureTime, String newArrivalTime) throws SQLException {
		
		String q = "Update Flight set flight_arrivalTime='%s',flight_departureTime='%s' where flight_id=%s";
		String query = String.format(q, newArrivalTime,newDepartureTime,flightID);
		db.executeSqlQueryUpdate(query);
	}

	
	/**
	 * checks if a flight Number is valid
	 * @param flightNumber
	 * @return {@link Boolean}
	 */
	public static boolean checkValidFlight(String flightNumber) {
		String q = "select * from Flight where flight_number = '%s'";
		String query = String.format(q, flightNumber);
		DatabaseWrapper db = new DatabaseWrapper();
		ResultSet rs = null;
		try {
			rs  = db.executeSqlQuerySelect(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(DatabaseUtil.ResultSetLength(rs)==1)
			return true;
		else
			return false;
	}


	/**
	 * return flights details of a flight
	 * @param flightNumber
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getFlightDetails(String flightNumber) throws SQLException {
			
		String q = "select flight_id,flight_numberOfSeats, flight_cost from Flight where flight_number = '%s'";
		String query  = String.format(q, flightNumber);
		DatabaseWrapper db = new DatabaseWrapper();
		ResultSet rs = db.executeSqlQuerySelect(query);
		return rs;
		}


	/**
	 * books tickets in a flight
	 * @param numberOfTickets
	 * @param flightID
	 * @throws SQLException
	 */
	public static void bookSeats(int numberOfTickets,int flightID) throws SQLException {
		int numberOfAvailableSeats = FlightService.getNumberOfAvailableSeats(flightID);
		
		String q = "update Flight set flight_numberOfSeats = %s where flight_id=%s";
		String query = String.format(q, numberOfAvailableSeats-numberOfTickets,flightID);
		DatabaseWrapper db = new DatabaseWrapper();
		db.executeSqlQueryUpdate(query);
		
	}


	/**
	 * get NUmber of available seats in a flight
	 * @param flightID
	 * @return Integer
	 * @throws SQLException
	 */
	private static int getNumberOfAvailableSeats(int flightID) throws SQLException {
		String query = "select flight_numberOfSeats from Flight where flight_id="+flightID;
		DatabaseWrapper db  = new DatabaseWrapper();
		ResultSet rs = db.executeSqlQuerySelect(query);
		while(rs.next()) {
			
			return rs.getInt(1);
		}
		return 0;
	}
	
}

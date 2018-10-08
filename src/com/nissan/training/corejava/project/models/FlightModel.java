package com.nissan.training.corejava.project.models;

import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.enums.FlightStatus;
import com.nissan.training.corejava.project.interfaces.IModel;

public class FlightModel implements IModel{

	public int flightID;
	private String flightArrival;
	private String flightDeparture;
	private String flightArrivalTime;
	private String flightDepartureTime;
	private String flightCompany;
	private String flightNumber;
	private int flightNumberOfSeats;
	private FlightStatus flightStatus;
	private String flightType;
	private int flightCost;
	
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public String getFlightArrival() {
		return flightArrival;
	}
	public void setFlightArrival(String flightArrival) {
		this.flightArrival = flightArrival;
	}
	public String getFlightDeparture() {
		return flightDeparture;
	}
	public void setFlightDeparture(String flightDeparture) {
		this.flightDeparture = flightDeparture;
	}
	public String getFlightArrivalTime() {
		return flightArrivalTime;
	}
	public void setFlightArrivalTime(String flightArrivalTime) {
		this.flightArrivalTime = flightArrivalTime;
	}
	public String getFlightDepartureTime() {
		return flightDepartureTime;
	}
	public void setFlightDepartureTime(String flightDepartureTime) {
		this.flightDepartureTime = flightDepartureTime;
	}
	public String getFlightCompany() {
		return flightCompany;
	}
	public void setFlightCompany(String flightCompany) {
		this.flightCompany = flightCompany;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public int getFlightNumberOfSeats() {
		return flightNumberOfSeats;
	}
	public void setFlightNumberOfSeats(int flightNumberOfSeats) {
		this.flightNumberOfSeats = flightNumberOfSeats;
	}
	public FlightStatus getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public FlightModel() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void save() throws SQLException {
		String q = "insert into Flight values(NULL, '%s','%s','%s','%s','%s','%s','%s','%s','%s',%s)";
		String query = String.format(q,  this.flightArrival,
										 this.flightDeparture,
										 this.flightArrivalTime,
										 this.flightDepartureTime,
										 this.flightCompany,
										 this.flightNumber,
										 this.flightNumberOfSeats,
										 this.flightStatus,
										 this.flightType,
										 this.flightCost
										 );
		DatabaseWrapper db = new DatabaseWrapper();
		db.executeSqlQueryInsert(query);
	}
	public int getFlightCost() {
		return flightCost;
	}
	public void setFlightCost(int flightCost) {
		this.flightCost = flightCost;
	}

}

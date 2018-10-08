package com.nissan.training.corejava.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nissan.training.corejava.project.enums.FlightStatus;
import com.nissan.training.corejava.project.enums.Validators;
import com.nissan.training.corejava.project.interfaces.IInputInterface;
import com.nissan.training.corejava.project.models.FlightModel;
import com.nissan.training.corejava.project.services.FlightService;
import com.nissan.training.corejava.project.utils.ReadInput;
import com.nissan.training.corejava.project.utils.Utility;

/**
 * The controller is responsible for all operations involving flight model and and flight database
 * @author NDH00159
 *
 */
public class FlightController implements IInputInterface {

	//flightservice that connects flight controller to database
	FlightService flightService = null;
	ReadInput readInput =null;
	FlightModel flightModel  = null;
	
	public FlightController() {
		flightService = new FlightService();
		readInput = new ReadInput();
	}

	/**
	 * show all flights
	 * @throws SQLException
	 */
	public void showAllFlights() throws SQLException {
		ArrayList<FlightModel> allFlights = 
					flightService.getAllFlights();
		
		//print labels 
		if(allFlights.size()!=0)
			Utility.printLabelsFlight();
		
		//loop through all Flights and display 
		for(FlightModel flight:allFlights) {
			System.out.print(flight.getFlightID() + "  ");
			System.out.print(flight.getFlightArrival() + "  ");
			System.out.print(flight.getFlightDeparture() + "  ");
			System.out.print(flight.getFlightArrivalTime() + "  ");
			System.out.print(flight.getFlightDepartureTime() + "  ");
			System.out.print(flight.getFlightNumber() + "  ");
			System.out.print(flight.getFlightCompany() + "  ");
			System.out.print(flight.getFlightStatus() + "  ");
			System.out.print(flight.getFlightNumberOfSeats() + "  ");
			System.out.print(flight.getFlightType() + "  ");
			System.out.print(flight.getFlightCost() + "  ");
			System.out.println();
		}
		
	}

	/**
	 * cancel a flight
	 * @param flightID
	 * @throws SQLException
	 */
	public void cancelFlight(int flightID) throws SQLException {
		flightService.canecelFlight(flightID);
		
	}

	/**
	 * change flight timings
	 * @param flightID
	 * @throws SQLException
	 */
	public void changeFlightTimings(int flightID) throws SQLException {
		System.out.print("\n>Enter new Departure time : ");
		String newDepartureTime = readInput.getInputString(Validators.EMPTYINPUT);
		
		System.out.print("\n>Enter new Arrival time : ");
		String newArrivalTime = readInput.getInputString(Validators.EMPTYINPUT);
		
		flightService.changeFLightTimings(flightID, newDepartureTime,newArrivalTime);
	}

	/**
	 * add a flight and infernally calls flightModel.save() method
	 * @throws SQLException
	 */
	public void addFlight() throws SQLException {
		flightModel.save();
	}

	/**
	 * takes input and creates in flightModel
	 */
	@Override
	public void takeInput() {
		flightModel = new FlightModel();
		
		System.out.print("Enter FLight Number : ");
		flightModel.setFlightNumber(readInput.getInputString(Validators.EMPTYINPUT));
		
		System.out.print("Enter Company name : ");
		flightModel.setFlightCompany(readInput.getInputString(Validators.EMPTYINPUT));
		
		System.out.print("Enter Arrival : ");
		flightModel.setFlightArrival(readInput.getInputString(Validators.EMPTYINPUT));
		
		System.out.print("Enter Departure : ");
		flightModel.setFlightDeparture(readInput.getInputString(Validators.EMPTYINPUT));
		
		System.out.print("Enter Arrival Time : ");
		flightModel.setFlightArrivalTime(readInput.getInputString(Validators.EMPTYINPUT));
		
		System.out.print("Enter Departure Time : ");
		flightModel.setFlightDepartureTime(readInput.getInputString(Validators.EMPTYINPUT));
		
		
		System.out.print("Enter Capacity of FLight  : ");
		flightModel.setFlightNumberOfSeats(readInput.getInputInteger(Validators.EMPTYINPUT));
		
		System.out.print("Enter cost of a ticket : ");
		flightModel.setFlightCost(readInput.getInputInteger(Validators.AMOUNT));
		
		System.out.print("Enter type of flight [1=DOMESTIC , 2 =INTERNATIONAL] : ");
		int res = readInput.getInputInteger(Validators.EMPTYINPUT);
		if(res==1)
			flightModel.setFlightType("DOMESTIC");
		else 
			flightModel.setFlightType("INTERNATIONAL");
		
		flightModel.setFlightStatus(FlightStatus.ON_TIME);
	}

	/**
	 * lists all flights that are available
	 * @throws SQLException
	 */
	public static void showAllAvailableFLights() throws SQLException {
		System.out.print("\nAll available flights are\n");
		
		FlightService flightService = new FlightService();
		
		ArrayList<FlightModel> allFlights = 
				flightService.getAllAvailableFlights();
		
		if(allFlights.size()!=0)
			Utility.printLabelsFlight();
		
		for(FlightModel flight:allFlights) {
			System.out.print(flight.getFlightID() + "  ");
			System.out.print(flight.getFlightArrival() + "  ");
			System.out.print(flight.getFlightDeparture() + "  ");
			System.out.print(flight.getFlightArrivalTime() + "  ");
			System.out.print(flight.getFlightDepartureTime() + "  ");
			System.out.print(flight.getFlightNumber() + "  ");
			System.out.print(flight.getFlightCompany() + "  ");
			System.out.print(flight.getFlightStatus() + "  ");
			System.out.print(flight.getFlightNumberOfSeats() + "  ");
			System.out.print(flight.getFlightType() + "  ");
			System.out.print(flight.getFlightCost() + "  ");
			System.out.println();
		}
		
	}
	

}

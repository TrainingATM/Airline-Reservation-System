package com.nissan.training.corejava.project.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.nissan.training.corejava.project.enums.Validators;
import com.nissan.training.corejava.project.utils.ClearConsole;
import com.nissan.training.corejava.project.utils.ReadInput;

public class AdminController {
	
	Scanner in = null;
	ReadInput readInput = null;
	
	public AdminController() {
		in = new Scanner(System.in);
		readInput = new ReadInput();
	}
	public void start() {
		
		//clears the console
		ClearConsole.clearConsole();		
		System.out.print("\nLogged in succesfully as Admin");
		
		//creating flight controller object responsible for managing operations on Flight model
		FlightController flightController = new FlightController();
		
		while(true) {
			
			System.out.print("\n");
			
			System.out.print("\n>1. Show current flights ");
			System.out.print("\n>2. Show all available flights ");
			System.out.print("\n>3. Cancel a flight");
			System.out.print("\n>4. Change flight timings");
			System.out.print("\n>5. Add a new FLight ");
			System.out.print("\n>6. Exit ");
			System.out.print("\nYour choice :  ");
			
			int option = in.nextInt();
			int flightID;
			switch(option) {
			case 1:
				System.out.print("All Flights \n");
				try {
					//show all flights
					flightController.showAllFlights();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.print("\nAll Available Flights \n");
				try {
					FlightController.showAllAvailableFLights();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
				break;
			case 3:
				System.out.print("\nFlight Cancelletion\n");
				
				System.out.print("\nEnter Flight ID : ");
				//enter flight ID
				flightID = readInput.getInputInteger(Validators.EMPTYINPUT);
				
				try {
					
					//cancel flight
					flightController.cancelFlight(flightID);
					System.out.print("\n-- Flight cancelled");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			case 4:
				System.out.print("\nChange FLight Timings\n");
				System.out.println("Enter Flight ID : ");
				flightID = readInput.getInputInteger(Validators.EMPTYINPUT);
				
				try {
					//Change flight timings
					flightController.changeFlightTimings(flightID);
					System.out.print("\n-- Flight Timings changed");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.print("\nAdding new Flight\n");
				
				//take flight details and create flight model object
				flightController.takeInput();
				try {
					//add flight to db 
					flightController.addFlight();
					System.out.print("\n-- Succsfully Added FLight");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.print("\nClosing... Thank you");
				System.exit(0);
				
			default:
				System.out.print("\nWrong Input");
			}
			
		}
		
	}

}

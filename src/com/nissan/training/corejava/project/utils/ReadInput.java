package com.nissan.training.corejava.project.utils;

import java.util.Scanner;

import com.nissan.training.corejava.project.enums.Validators;
public class ReadInput {

	Validation validation;
	public ReadInput() {
		validation = new Validation();
	}
	
	
	/**
	 * 
	 * @param validator = {"PIN","EMAIL","PHONE_NUMBER"}
	 * @return Integer
	 */
	public int getInputInteger(Validators validator) {
		@SuppressWarnings("resource")
		Scanner in  = new Scanner(System.in);
		
		while(true) {
			String value = in.nextLine();
			
			if(validation.validate(validator,value)) {
				return Integer.parseInt(value);
			}
			System.out.println("\n Invalid input. Type again");
		}
	}
	/**
	 * 
	 * @param validator
	 * @return String
	 */
	public String getInputString(Validators validator) {
		@SuppressWarnings("resource")
		Scanner in  = new Scanner(System.in);
		while(true) {
			String value = in.nextLine();
			
			if(validation.validate(validator,value)) {
				return value;
			}
			System.out.println("\nWrong input. Type again");
		}
	}

}

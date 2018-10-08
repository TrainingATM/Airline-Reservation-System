package com.nissan.training.corejava.project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nissan.training.corejava.project.enums.Validators;

public class Validation {

	private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	// static Pattern object, since pattern is fixed
	private static Pattern pattern;
	// non-static Matcher object because it's created from the input String
	private static Matcher matcher;

	/**
	 * Validates a string based on the validator @param passed
	 * @param validator
	 * @param data
	 * @return Boolean
	 */
	public Boolean validate(Validators validator, String data) {
		
		if(validator == Validators.PIN)
				return validatePin(data);
		if(validator == Validators.EMAIL)
				return validateEmail(data);
		if(validator == Validators.PHONE_NUMBER)
			return validatePhoneNumber(data);
		if(validator == Validators.CARD_NUMBER)
				return validateCardNumber(data);
		if(validator == Validators.DOB)
				return validateDOB(data);
		if(validator == Validators.EMPTYINPUT)
				return !data.isEmpty();
		if(validator == Validators.AMOUNT)
				return validateAmount(data);
		
		return true;
	}
	
	private Boolean validateAmount(String data) {
		try {
			double amount = Double.parseDouble(data);
			if(amount < 0)
					return false;
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	
	}

	/**
	 * validates card pin
	 * @param pin
	 * @return Boolean
	 */
	private Boolean validatePin(String pin) {
		if(pin.length()==4)return true;
		else return false;
	}
	
	/**
	 * validates email
	 * @param email
	 * @return Boolean
	 */
	private Boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * validates phone number
	 * @param phoneNumber
	 * @return Boolean
	 */
	private  Boolean validatePhoneNumber(String phoneNumber) {
		return phoneNumber.length()==10;
	}
	/**
	 * validates card numbers
	 * @param cardNumber
	 * @return
	 */
	private Boolean validateCardNumber(String cardNumber ){
		return cardNumber.length()==16;
	}
	
	/**
	 * validates date of birth
	 * @param dob
	 * @return Boolean
	 */
	private Boolean validateDOB(String dob) {
		String[] split = dob.split("/");
		if(split.length!=3) return false;
		
		if(Integer.parseInt(split[0])<0 || Integer.parseInt(split[0])>31)
			return false;
		if(Integer.parseInt(split[1])<0 || Integer.parseInt(split[1])>12)
			return false;
		if(Integer.parseInt(split[2])<0 || Integer.parseInt(split[2])>5000)
			return false;
		return true;
		
	}
}


package com.nissan.training.corejava.project.controller;

import java.sql.SQLException;

import com.nissan.training.corejava.project.enums.Validators;
import com.nissan.training.corejava.project.interfaces.IInputInterface;
import com.nissan.training.corejava.project.interfaces.IRegistrationInterface;
import com.nissan.training.corejava.project.models.CustomerModel;
import com.nissan.training.corejava.project.utils.MD5;
import com.nissan.training.corejava.project.utils.ReadInput;

/**
 * Customer Registration class
 * implements input interface and registration interface
 * @author NDH00159
 *
 */
public class CustomerRegistrationController implements IInputInterface, IRegistrationInterface{

	CustomerModel custModel = null;
	ReadInput readInput = null;
	public CustomerRegistrationController() {
		custModel = new CustomerModel();
		readInput = new ReadInput();
	}

	/**
	 * registers an customer
	 */
	@Override
	public void register() throws SQLException {
		custModel.save();
		
	}

	/**
	 * takes customer registration details and creates custModel object
	 */
	@Override
	public void takeInput() {
		
		System.out.print("\nEnter your Name :  ");
		custModel.setCustName(readInput.getInputString(Validators.EMPTYINPUT));
		
		System.out.println("Enter your Email : ");
		custModel.setCustEmail(readInput.getInputString(Validators.EMAIL));
		
		while(true) {
			String password;
			System.out.print("Enter your Password : ");
			password = readInput.getInputString(Validators.PASSWORD);
			
			System.out.print("Enter password again : ");
			if(password.equals(readInput.getInputString(Validators.EMPTYINPUT))) {
				custModel.setCustPassword(MD5.getHash(password));
				break;
			}
			else
				System.err.print("\nPassword do not match. Enter Again");
		}
		
		
		System.out.print("\nEnter Date of Birth (DD/MM/YYYY) : ");
		custModel.setCustDob(readInput.getInputString(Validators.DOB));
		
		System.out.print("Enter Contact Number : ");
		custModel.setCustContactNumber(readInput.getInputString(Validators.PHONE_NUMBER));
	} 

}

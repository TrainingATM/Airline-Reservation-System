package com.nissan.training.corejava.project.controller;

import java.sql.SQLException;

import com.nissan.training.corejava.project.enums.Validators;
import com.nissan.training.corejava.project.interfaces.IInputInterface;
import com.nissan.training.corejava.project.interfaces.IRegistrationInterface;
import com.nissan.training.corejava.project.models.AdminModel;
import com.nissan.training.corejava.project.services.AdminRegistrationService;
import com.nissan.training.corejava.project.utils.MD5;
import com.nissan.training.corejava.project.utils.ReadInput;

public class AdminRegistrationController implements IRegistrationInterface, IInputInterface {

	
	String email;
	String password;
	AdminRegistrationService adminRegistrationService = null;
	AdminModel adminModel = null;
	ReadInput readInput = null;
	
	public AdminRegistrationController() {
		adminRegistrationService = new AdminRegistrationService();
		adminModel = new AdminModel();
		readInput = new ReadInput();
	}

	/**
	 * Registers a admin, calls model.save() method internally
	 * @throws SQLException
	 */
	@Override
	public void register() throws SQLException {
		if(adminModel!=null)
			adminModel.save();
		
	}

	/**
	 * takes admin registration details and stores in adminModel object 
	 */
	@Override
	public void takeInput() {
		
		System.out.print("\n> Enter Email : ");
		
		// read integer input with proper validation
		this.email = readInput.getInputString(Validators.EMAIL);
		adminModel.setAdminEmail(this.email);
		
		System.out.print("> Enter Password : ");
		this.password = readInput.getInputString(Validators.PASSWORD);
		adminModel.setAdminPassword(MD5.getHash(this.password));
		
		
		System.err.println("****** NOTE : For purpose of the demo please enter the following admin_registration_token : \"abcd\"");
		System.err.println("****** NOTE : In real world application this code will be given to the admin\n");
		System.out.print("Enter admin_registration_token given : ");
		String adminRegistrationToken = readInput.getInputString(Validators.NO_VALIDATE);
		
		if(!AdminRegistrationService.validateToken(adminRegistrationToken)) {
			System.out.print("\nInvalid token. Please enter correct Admin Registration Token");
		}
		
		
	}
	
	
	

}

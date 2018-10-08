package com.nissan.training.corejava.project.controller;

import java.sql.SQLException;

import com.nissan.training.corejava.project.enums.Validators;
import com.nissan.training.corejava.project.interfaces.IInputInterface;
import com.nissan.training.corejava.project.interfaces.ILoginInterface;
import com.nissan.training.corejava.project.services.CustomerLoginService;
import com.nissan.training.corejava.project.utils.MD5;
import com.nissan.training.corejava.project.utils.ReadInput;
import com.nissan.traning.corejava.project.exception.UserNotFoundException;

/**
 * Responsible for logging CUstomer
 * @author NDH00159
 *
 */
public class CustomerLoginController implements IInputInterface, ILoginInterface {

	private String custEmail;
	private String custPassword;
	
	ReadInput readInput = null;
	CustomerLoginService customerLoginService = null;
	
	public CustomerLoginController() {
		readInput = new ReadInput();
		customerLoginService = new CustomerLoginService();
	}

	@Override
	public int login() throws UserNotFoundException, SQLException{
		
		return customerLoginService.checkCustomerInDB(this.custEmail,this.custPassword);
		
	}

	@Override
	public void takeInput() {
		System.out.print("\nEnter Email : ");
		this.custEmail = readInput.getInputString(Validators.EMAIL);
		
		System.out.print("Enter Password : ");
		this.custPassword = MD5.getHash(readInput.getInputString(Validators.PASSWORD));
		
	}

}

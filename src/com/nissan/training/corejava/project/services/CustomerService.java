package com.nissan.training.corejava.project.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.models.CustomerModel;

public class CustomerService {

	public CustomerService() {
		
	}
	
	/**
	 * Utility for getting a customer name from customer ID
	 * @param custID
	 * @return
	 */
	public static String getCustomerName(int custID) {
		String q = "select cust_name from Customer where cust_id = "+custID;
		DatabaseWrapper db = new DatabaseWrapper();
		String customerName = null;
		try {
			ResultSet rs = db.executeSqlQuerySelect(q);
			while(rs.next()) {
				customerName = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerName;
	}
	public void saveCustomer(CustomerModel customerModel) {
		
	}
	
	public void getCustomer() {
		
	}
	
	public void getCustomer(int custID) {
		
	}
	
	public void getSavedCards(int custID) {
		
	}
	
	

}

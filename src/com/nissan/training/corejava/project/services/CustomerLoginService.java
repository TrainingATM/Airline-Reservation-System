package com.nissan.training.corejava.project.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseUtil;
import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.traning.corejava.project.exception.UserNotFoundException;

public class CustomerLoginService{

	ResultSet rs;
	DatabaseWrapper db = new DatabaseWrapper();
	public CustomerLoginService() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * check customer and returns customerID
	 * @param email
	 * @param passwd
	 * @return
	 * @throws UserNotFoundException
	 * @throws SQLException
	 */
	public int checkCustomerInDB(String email, String passwd) throws UserNotFoundException, SQLException{
		
		String q = "select * from Customer where cust_email='%s'and cust_password='%s'";
		String query = String.format(q, email,passwd);
		rs = db.executeSqlQuerySelect(query);
		switch(DatabaseUtil.ResultSetLength(rs)) {
		case 1:
			while(rs.next()) {
				return rs.getInt(1);
			}
			break;
		default:
			throw new UserNotFoundException("Your credentiala are wrong");
		}
		return -1;
	}


}

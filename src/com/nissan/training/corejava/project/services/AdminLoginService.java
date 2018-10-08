package com.nissan.training.corejava.project.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseUtil;
import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.traning.corejava.project.exception.UserNotFoundException;

public class AdminLoginService{

	DatabaseWrapper dbb  = new DatabaseWrapper();
	ResultSet rs;
	public AdminLoginService() {
		// TODO Auto-generated constructor stub
	}
	
	public int checkAdmin(String email, String passwd) throws UserNotFoundException, SQLException{
			
		String q = "select * from AdminTable where admin_email='%s'and admin_password='%s'";

		String query = String.format(q, email,passwd);
		rs = dbb.executeSqlQuerySelect(query);
		
		switch(DatabaseUtil.ResultSetLength(rs)) {
		case 1:
			while(rs.next()) {
				
			}return 1;
		default:
			throw new UserNotFoundException("Your credentiala are wrong");
		}
	}

}

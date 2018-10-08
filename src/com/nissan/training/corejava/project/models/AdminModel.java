package com.nissan.training.corejava.project.models;

import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.interfaces.IModel;

public class AdminModel implements IModel{
	private String adminEmail;
	private String adminPassword;
	
	public AdminModel() {
		// TODO Auto-generated constructor stub
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public void save() throws SQLException {
		
		String str = "insert into AdminTable values('%s','%s','%s')";
		String query = String.format(str,adminEmail, adminPassword,"a4bvsdhg4g");
		DatabaseWrapper db = new DatabaseWrapper();
		db.executeSqlQueryInsert(query);

	}

}

package com.nissan.training.corejava.project.models;

import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.training.corejava.project.interfaces.IModel;

public class CustomerModel implements IModel {

	
	private String custName;
	private String custEmail;
	private String custPassword;
	private String custDob;
	private String custContactNumber;
	
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	public String getCustDob() {
		return custDob;
	}
	public void setCustDob(String custDob) {
		this.custDob = custDob;
	}
	public String getCustContactNumber() {
		return custContactNumber;
	}
	public void setCustContactNumber(String custContactNumber) {
		this.custContactNumber = custContactNumber;
	}
	public CustomerModel() {
		// TODO Auto-generated constructor stub
	}
	public void save() throws SQLException {
		String q = "insert into Customer values(NULL,'%s', '%s','%s','%s','%s')";
		
		String query  = String.format(q,this.custName,this.custEmail,this.custPassword,this.custDob,this.custContactNumber);

		DatabaseWrapper db = new DatabaseWrapper();
		db.executeSqlQueryInsert(query);
	}
}

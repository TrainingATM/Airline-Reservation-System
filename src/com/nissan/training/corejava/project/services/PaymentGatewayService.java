package com.nissan.training.corejava.project.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.nissan.training.corejava.project.doa.DatabaseUtil;
import com.nissan.training.corejava.project.doa.DatabaseWrapper;
import com.nissan.traning.corejava.project.exception.CardNotFoundException;

public class PaymentGatewayService {

	public PaymentGatewayService() {
		// TODO Auto-generated constructor stub
	}
	

	public Boolean verifyCardDetails(String cardNumber, String pin) throws SQLException, CardNotFoundException {
		
		String q = "select * from Card where card_number = '%s' and card_pin = '%s'";
		String query = String.format(q, cardNumber,pin);
		
		DatabaseWrapper db = new DatabaseWrapper();
		ResultSet rs = db.executeSqlQuerySelect(query);
		if(DatabaseUtil.ResultSetLength(rs)==0) {
			throw new CardNotFoundException("cards details were wrong. Enter again");
		}else {
			return true;
		}
	}

}

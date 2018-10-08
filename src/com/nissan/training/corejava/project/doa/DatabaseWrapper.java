package com.nissan.training.corejava.project.doa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class DatabaseWrapper {

	Connection con;
	public DatabaseWrapper() {
		con = (Connection) ConnectionFactory.getConnection();
	}
	
	public void closeConnection() {
		try {
			ConnectionFactory.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet executeSqlQuerySelect(String query) throws SQLException {
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery(query); 
		return rs;
	}
	
	public void executeSqlQueryInsert(String query) throws SQLException {
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
		
	}
	public void executeSqlQueryUpdate(String query) throws SQLException {
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
	}
	

}

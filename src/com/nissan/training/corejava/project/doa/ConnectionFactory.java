package com.nissan.training.corejava.project.doa;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connect to Database
 * @author 10G
 */

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/airlinesystem";
    
    public static final String USER = "root";
    public static final String PASS = "root";
    
    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(URL, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
    
    public static void closeConnection(Connection connection) throws SQLException {
    	if(connection!=null)
    		connection.close();
    }
}

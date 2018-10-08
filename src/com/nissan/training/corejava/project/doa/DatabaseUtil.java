package com.nissan.training.corejava.project.doa;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

	public DatabaseUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static int ResultSetLength(ResultSet rs) {
		int count = 0;
		try {
			while(rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}

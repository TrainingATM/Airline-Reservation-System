package com.nissan.training.corejava.project.interfaces;
import java.sql.SQLException;

import com.nissan.traning.corejava.project.exception.UserNotFoundException;

public interface ILoginInterface {
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return ID
	 * @throws SQLException 
	 * @throws CustomerNotFoundException
	 */
	public abstract int login() throws UserNotFoundException, SQLException;
}

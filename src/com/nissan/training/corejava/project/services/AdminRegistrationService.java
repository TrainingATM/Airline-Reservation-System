package com.nissan.training.corejava.project.services;

public class AdminRegistrationService {

	public AdminRegistrationService() {
		// TODO Auto-generated constructor stub
	}

	public static boolean validateToken(String adminRegistrationToken) {
		
		return adminRegistrationToken.equals("abcd");
	}


}

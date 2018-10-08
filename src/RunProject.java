import java.sql.SQLException;
import java.util.Scanner;
import com.nissan.training.corejava.project.controller.AdminController;
import com.nissan.training.corejava.project.controller.AdminLoginController;
import com.nissan.training.corejava.project.controller.AdminRegistrationController;
import com.nissan.training.corejava.project.controller.CustomerController;
import com.nissan.training.corejava.project.controller.CustomerLoginController;
import com.nissan.training.corejava.project.controller.CustomerRegistrationController;
import com.nissan.traning.corejava.project.exception.UserNotFoundException;

/**
 * run the project
 * @author NDH00159
 *
 */
public class RunProject {

	public RunProject() {}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//this is git
		//this is registration and Login flow
		Scanner in = new Scanner(System.in);
		
		while(true) {
			//options to login or register
			System.out.print("\n>Enter 1 to Register as Admin ");
			System.out.print("\n>Enter 2 to Register as Customer");
			System.out.print("\n>Enter 3 to Login as Admin");
			System.out.print("\n>Enter 4 to Login as Customer");
			System.out.print("\n>Enter 5 to Exit ");
			System.out.print("\n Your choice :  ");
			
			int option = in.nextInt();
			
			switch(option) {
			case 1:
				System.out.print("\nAdmin Registration : ");
				//create admin registration controller object
				AdminRegistrationController adminRegistrationController = new AdminRegistrationController();
				
				//take admin registration details
				adminRegistrationController.takeInput();
				try {
					//register a admin
					adminRegistrationController.register();
					System.out.print("\n-- Registartion successfull");
				} catch (SQLException e) {
					System.err.println("\n-- Registartion not succesfull. Email ID already exists in DB");
				}
			
				break;
			case 2:
				////create registration controller object
				CustomerRegistrationController customerRegistrationController = new CustomerRegistrationController();
				
				//take customer registration details
				customerRegistrationController.takeInput();
				
				try {
					//register a customer
					customerRegistrationController.register();
					System.out.print("\n-- Registartion successfull");
				} catch (SQLException e) {
					System.err.print("\n-- Registartion not succesfull. Email ID already exists in DB");
				}
		
				break;
			case 3:
				//create admin login controller
				AdminLoginController adminLoginController = new AdminLoginController();
				
				//take admin login details
				adminLoginController.takeInput();
				try {
					//login a register
					adminLoginController.login();
					
					//once successfully logged in transfer control to admincontroller object
					AdminController adminController = new AdminController();
					
					//opening admin page or console
					adminController.start();
					
				} catch (UserNotFoundException  e) {
					System.out.println("\n-- Wrong Credentials");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				//create customer login controller
				CustomerLoginController customerLoginController = new CustomerLoginController();
				
				//take customer login details
				customerLoginController.takeInput();
				try {
					
					//login a customer, the method returns a unique customer ID
					int customerId = customerLoginController.login();
					
					//once successfully logged in transfer control to customerController object
					CustomerController customerController = new CustomerController(customerId);
					
					//opening customer page or console
					customerController.start();
					
				} catch (UserNotFoundException  e) {
					System.out.print("\n-- Wrong Credentials");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Closing... Thank you");
				System.exit(0);
			default:
				System.out.println("Wrong choice");
			}
			System.out.println("\n\n\n");
		}

	}

}

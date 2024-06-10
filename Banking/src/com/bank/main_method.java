package com.bank;

import java.sql.SQLException;
import java.util.Scanner;

public class main_method {
	public static void main(String args[]) throws SQLException {
		Scanner sc = new Scanner(System.in);//scanner object  to take inputs from the user
		while (true) {

			System.out.println("WELCOME TO ABCD BANK HYDERABAD...\n");
			System.out.println("1.Open New Account");
			System.out.println("2.Log into Bank Account");
			System.out.println("3.Exit");
			System.out.println("\nEnter  operation to perform: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				new acc();
				break;

			case 2:

				login.signin();

				break;

			case 3:
				System.out.println("Exiting bye.....!");
				System.exit(0); // Exiting the application wantedly
				break;
			default: {
				System.out.println("Enter valid Operation..");
			}
			}

		}

	}

}

package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class login {

	public static void signin() throws SQLException {
		Scanner scc = new Scanner(System.in);// scanner object to take inputs from the user

		int count = 0;

		for (int i = 0; i <= 3; i++) {// loop to rotate the specified times to set the limits
			if (count == 3) {// to set the lock feature
				System.out.println("acc is locked..\ncontact to  the bank Manager");
				System.exit(0);// forecefully exits from the application
			} else {
				Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
						"root", "root");// db connection
				String q1 = "SELECT acc_no FROM acc_details";
				PreparedStatement pst = connection.prepareStatement("q1");
				ResultSet rs = pst.executeQuery(q1);
				while (rs.next()) {
					// Display values
					System.out.println("ABCD Bank");// bank name

					System.out.println("acc_no: " + rs.getString("acc_no"));
				}

				System.out.println("Enter Acc No:");// for login purpose
				int acc1 = scc.nextInt();
				String acc_no1 = String.valueOf(acc1);

				System.out.println("Enter pass");
				int pass1 = scc.nextInt();
				String password1 = String.valueOf(pass1);

				PreparedStatement pst1 = (PreparedStatement) connection
						.prepareStatement("Select count(*)from  acc_details where acc_no=? and password=?");
				// checks weather the given credentials are correct or not
				pst1.setString(1, acc_no1);
				pst1.setString(2, password1);
				ResultSet rs1 = pst1.executeQuery();

				while (rs1.next()) {// if credendtials are true then enters into application
					int response = rs1.getInt(1);
					if (response > 0) {
						System.out.println("Welcome To Banking Application");
						afterlogin();

					}

				}

				count++;
				int remain = 3 - count;// to get the remaining attemts
				System.out.println("Please Enter Correct details\nRemaining attempts:" + remain);

			}

		}

	}

	private static void afterlogin() throws SQLException {
		UserbankAccount user = new UserbankAccount();

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("2.Show Acc Details");
			System.out.println("3.Show Balance");
			System.out.println("4.Deposit");
			System.out.println("5.Withdraw");
			System.out.println("6.Transfer Money");
			System.out.println("7.Transaction History");
			System.out.println("8.Exit");
			System.out.println("Enter any operation to perform: ");
			int choice = sc.nextInt();
			// takes the details from the user
			switch (choice) {
			case 1:
				new acc();
				break;

			case 2:
				acc.getaccdetails();
				break;

			case 3:
				user.CheckBalance();
				break;

			case 4:

				System.out.println("Enter the amount to deposit:");
				user.deposit = sc.nextInt();
				user.Deposit(user.deposit);

				break;

			case 5:

				System.out.println("Available balance is:" + user.getbalance());
				System.out.println("Enter the amount to with_draw:");
				user.withdraw = sc.nextInt();
				user.Withdraw(user.withdraw);
				break;
			case 6:

				System.out.println("Enter Account number:");
				int accno = sc.nextInt();
				System.out.println("Enter amount:");
				user.transfer = sc.nextInt();
				user.transfer_mny(accno, user.transfer);

				break;
			case 7:
				UserbankAccount.deposit_transactions();
				UserbankAccount.withdraw_transactions();
				UserbankAccount.transfer_transactions();
				break;
			case 8:
				System.out.println("Exiting bye.....!");
				System.exit(0); // Exiting the application wantedly
				break;
			default:
				System.out.println("choose right option");

				UserbankAccount.sleep(); // showing some delay while
			} // any wrong inputs
		}
	}
}

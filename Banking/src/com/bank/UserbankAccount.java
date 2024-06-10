package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserbankAccount implements bank {
	private int balance = 1000; // mminimum bank balance
	int withdraw; // used for withdrawal of amt
	int deposit; // used for deposit of amt
	int transfer;// used for transfer money count

	public static final String url = "jdbc:MySQL://localhost:3306/bank";
	public static final String user = "root";
	public static final String pass = "root";
	// db connection

	public int getbalance() {
		return balance;
	} // to get the available bal

	@Override // withdraw
	public int Withdraw(int w_balance) {
		int count = 0;
		for (int k = 0; k < 3; k++) {// loop to rotate
			if (count == 3) {// count to set the withdraw limit
				System.out.println("Withdrawal limit has been reached for today\nTry after 24 hrs");

			} else {
				if (w_balance < balance) {

					try {
						Connection con = DriverManager.getConnection(url, user, pass);
						// db connection
						PreparedStatement pst = con.prepareStatement("insert into withdraw values(?)");
						// innsert query
						pst.setInt(1, w_balance);
						pst.executeUpdate();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					this.balance -= w_balance;
				} else {
					System.out.println("in sufficient balance");
				}
				System.out.println("Available balance:" + balance);

			}
			break;
		}
		return balance;// returns the balance
	}

	@Override // deposit
	public int Deposit(int balance) {// method for deposit
		try {
			Connection con = DriverManager.getConnection(url, user, pass);
			// db connection
			PreparedStatement pst = con.prepareStatement("insert into deposit values(?)");
			pst.setInt(1, balance);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.balance += balance;
		System.out.println("Available balance:" + getbalance());
		return getbalance();
	}

	@Override // to check balance
	public int CheckBalance() {
		System.out.print("wait few sec's meanwhile we fetch ur data");
		for (int i = 0; i <= 4; i++) {
			System.out.print("."); // to get 5 loading points
			sleep(); // calling sleep method
		}

		System.out.println("\nAvailable balance:" + balance + "\n");
		return getbalance();
	}

	public static void sleep() { // sleep method for those above loading pts
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void deposit_transactions() throws SQLException {
		System.out.println("\nABCD Bank");
		Connection con = DriverManager.getConnection(url, user, pass);
		// db conection
		String QUERY = "SELECT deposit FROM deposit";
		// sql query
		PreparedStatement pst = con.prepareStatement("QUERY");

		System.out.println("Fetching Deposit records please wait....\n");
		ResultSet rs = pst.executeQuery(QUERY);

		while (rs.next()) {
			// Display values

			System.out.println("Deposit: " + rs.getInt("deposit"));
		}

	}

	public static void withdraw_transactions() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, pass);

		String QUERY = "SELECT withdraw FROM withdraw";

		PreparedStatement pst = con.prepareStatement("QUERY");

		System.out.println("\nFetching  withdraw records please wait....");
		ResultSet rs = pst.executeQuery(QUERY);

		while (rs.next()) {
			// Display values

			System.out.println("withdraw: " + rs.getInt("withdraw"));
		}
	}

	public static void transfer_transactions() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, pass);

		String QUERY = "SELECT accno,transfer FROM transactions";

		PreparedStatement pst = con.prepareStatement("QUERY");

		System.out.println("\nFetching  transfer records please wait....");
		ResultSet rs = pst.executeQuery(QUERY);

		while (rs.next()) {
			// Display values
			System.out.println("acc no:" + rs.getInt("accno"));
			System.out.println("transfer: " + rs.getInt("transfer"));
		}
	}

	public int transfer_mny(int accno, int transfer) {
		
		int count = 0;
		if (transfer < 100000) { // one time transfer limit is less than a lakh
			for (int k = 0; k < 3; k++) {
				if (count == 3) {// count to set limit for transfer the money
					System.out.println("Transfer limit has been reached for today\nTry after 24 hrs");

				} else {

					try {
						Connection con = DriverManager.getConnection(url, user, pass);
						// db connection
						PreparedStatement pst = con.prepareStatement("insert into transactions values(?,?)");
						pst.setInt(1, accno);
						pst.setInt(2, transfer);
						pst.executeUpdate();

					} catch (SQLException e) {
						e.printStackTrace();
					}

					balance = balance - transfer;
					System.out.println("Transfered Amount is:" + transfer);

				}
				break;
			}

		} else {
			System.out.println("Withdrawal limit Exceeded");
		}
		return getbalance();
	}
}

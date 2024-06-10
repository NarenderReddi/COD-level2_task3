package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;

public class acc {

	private String name;
	private String father_name;
	private String aadhar_no;
	private String mobile_number;
	private String pan_card;
	private int acc_no;
	private String password;

	public static final String url = "jdbc:MySQL://localhost:3306/bank";
	public static final String user = "root";
	public static final String pass = "root";

	acc() {

		Scanner sc = new Scanner(System.in);
		System.out.println("wait While we generate Acc number for u.. ");
		Random ran = new Random();
		int min = 12345689;//minimum number
		int max = 99999999;//max number
		acc_no = ran.nextInt(max - min + 1) + min;
		//randomly it generates the Acc Number with the given Conditions
		System.out.println("Your Acc no is: " + acc_no);
		System.out.println("Enter Password:");
		password = sc.nextLine();

		System.out.println("Enter Name:");
		name = sc.nextLine();
		System.out.println("Enter Father name:");
		father_name = sc.nextLine();
		System.out.println("Enter Aadhar No:");
		aadhar_no = sc.nextLine();
		System.out.println("Enter Mobile Number:");
		mobile_number = sc.nextLine();
		System.out.println("Enter Pan card number:");
		pan_card = sc.nextLine();
		//all the details required to register
		System.out.println("Registered Successfully.");

		try {
			Connection con = DriverManager.getConnection(url, user, pass);
			//db connection
			PreparedStatement pst = con.prepareStatement("insert into acc_details values(?,?,?,?,?,?,?)");
			pst.setLong(1, getacc_no());
			pst.setString(2, getpassword());
			pst.setString(3, getname());
			pst.setString(4, getfather_name());
			pst.setString(5, getaadhar_no());
			pst.setString(6, getmobile_number());
			pst.setString(7, getpan_card());
			//inserts into the db
			pst.executeUpdate();
			System.out.println("Data Saved Succesffully\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void getaccdetails() {

		try {
			Connection con = DriverManager.getConnection(url, user, pass);

			String QUERY = "SELECT acc_no, name, aadhar_no, pan_card FROM acc_details";

			PreparedStatement pst = con.prepareStatement("QUERY");

			System.out.println("Fetching records please wait....\n");
			ResultSet rs = pst.executeQuery(QUERY);
			while (rs.next()) {
				// Display values
				System.out.println("ABCD Bank");

				System.out.println("acc_no: " + rs.getString("acc_no"));
				System.out.println("IFSC CODE:0015kkAB012");
				System.out.println("name: " + rs.getString("name"));
				System.out.println("aadhar_no: " + rs.getString("aadhar_no"));
				System.out.println("pan_card: " + rs.getString("pan_card") + "\n");
			}//gets the complete profile details from db **EXCEPT PASSWORD**

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getname() {

		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getfather_name() {
		return father_name;
	}

	public void setfather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getaadhar_no() {
		return aadhar_no;
	}

	public void setaadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	public String getmobile_number() {
		return mobile_number;
	}

	public void setmobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getpan_card() {
		return pan_card;
	}

	public void setpan_card(String pan_card) {
		this.pan_card = pan_card;
	}

	public int getacc_no() {

		return acc_no;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getpassword() {

		return password;
	}

	public void setacc_no(int acc_no) {
		this.acc_no = acc_no;
	}
	//all are getters and setters 

}

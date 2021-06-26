package com.javaproject;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javaproject.Volunteer;

public class MySQLClass {
	private static Connection connect;
	private static Statement stmt;
	private static PreparedStatement ps = null;
/*		
	public static void main(String[] args) throws Exception {
	
		Volunteer volunteer1=new Volunteer(0, "volunteer1", "eid8@id.com", "8888888888");
		
		displayFullData();
		connect.close(); 
	}
*/	
	public static void connectToDB() {
		System.out.println("Connecting to Database");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			connect=DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6420212","sql6420212","ElkfZUKfAB");  
			System.out.println("Connected");
			
		}catch (Exception e) {
			System.out.println("Connection failed: "+e.getMessage());
		}
		
	}
	
	public static ResultSet executeQuery(String query) throws Exception {
		ResultSet rs=null;
		System.out.println("Executing query: "+query);
		try {
			stmt=connect.createStatement();  
			System.out.println("statement created");
			rs=stmt.executeQuery(query); 
			System.out.println("Executed Query Successfully!!");		
		}catch (Exception e) {
			System.out.println("Query execution failed");
		}
		
		return rs;
	}
	
	public static String insertData(Volunteer person) throws Exception {
		connectToDB();
		
		System.out.println("Inserting to database Name: "+person.getName()+"  Email :"+person.getEmail()+"Phone: "+person.getPhone());
		String insertQuery="INSERT INTO `Employee`(`NAME`, `EMAILID`, `PHONE`) VALUES (?,?,?);";
		System.out.println("Prepared Query:"+insertQuery);
		ps=connect.prepareStatement(insertQuery);
		ps.setString(1, person.getName());
		ps.setString(2, person.getEmail());
		ps.setString(3, person.getPhone());
		try {
			Boolean result=ps.execute();
			System.out.println("Insert Success!! Returned result "+result);		    
		}catch(Exception e) {
			System.out.println("Insertion failed: "+e.getMessage());
			return returnErrorMessage(e);
		}
		connect.close();
		return "Registration success";
	}
	
	public static void getFullData() throws Exception {
		String selectAllQuery="SELECT * FROM Employee";
		ResultSet fullDataRS=executeQuery(selectAllQuery);
		while(fullDataRS.next())   
		System.out.println( "EID:"+fullDataRS.getInt("EID")+" NAME: "+fullDataRS.getString("NAME")+" Email: "+fullDataRS.getString("EMAILID")+" Phone: "+fullDataRS.getString("PHONE"));  
		
	}
	public static String returnErrorMessage(Exception e) {
		if(e.getMessage().contains("Duplicate entry")) {
			if(e.getMessage().contains("EMAILID")){
				System.out.println("Entered Email id already exists in database");
				return "Entered Email id already exists in database";
			 }
			else if (e.getMessage().contains("PHONE")) {
				System.out.println("Entered Number already exists in database");
				return "Entered Phone already exists in database";
			}
		}
		return "UNKNOWN ERROR EXCEPTION. CONTACT ADMINISTRATOR. THIS SCREENSHOT MAY HELP TO FIX THE ISSUE";
	}
	public static void displayFullData(PrintWriter out) throws Exception {
		connectToDB();
		String selectAllQuery="SELECT * FROM Employee";
		ResultSet fullDataRS=executeQuery(selectAllQuery);
		while(fullDataRS.next())   
		out.println( "EID:"+fullDataRS.getInt("EID")+" NAME: "+fullDataRS.getString("NAME")+" Email: "+fullDataRS.getString("EMAILID")+" Phone: "+fullDataRS.getString("PHONE")+"<br>");  
		connect.close();
	}
}

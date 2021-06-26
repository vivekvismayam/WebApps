package com.javaproject;

import java.io.IOException;
import java.io.PrintWriter;
import com.javaproject.MySQLClass;
import com.javaproject.Volunteer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//s@WebServlet("/register") 
public class RegisterServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException  {
		Volunteer volunteer;
		PrintWriter out=res.getWriter();
		try {
			String name=req.getParameter("name");
			String email=req.getParameter("mail_id");
			String phone=req.getParameter("phone_number");
			volunteer=new Volunteer(0,name,email,phone);
			if(!volunteer.checkVolunteerDataValidity()) {
				out.println("<br>Fill all details before submitting");
				
			}
			else {
				String dbUpdateStatus=MySQLClass.insertData(volunteer);		
				out.println("<h1>"+dbUpdateStatus+"<//h1> ");
			}
		}
		catch (Exception e) {
			out.println("<h1>Unknown Error on Servelet<//h1> <br>");	
			System.out.println(e.getMessage());
		}
		out.println("<a href=\"/WebApp\">Back</a><br>");
	}
}

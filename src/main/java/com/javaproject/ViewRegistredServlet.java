package com.javaproject;

import java.io.IOException;
import java.io.PrintWriter;
import com.javaproject.MySQLClass;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/viewall") 
public class ViewRegistredServlet  extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException  {
		
		PrintWriter out=res.getWriter();
		out.println("<a href=\"/WebApp\">Back</a><br>");
		try {
			MySQLClass.displayFullData(out);
			out.println("----------------Completed---------------");
		}
		catch (Exception e) {
			out.println("<h1>Unknown Error on Servelet</h1> <br>");
			System.out.println(e.getMessage());
		}
		
	}
}

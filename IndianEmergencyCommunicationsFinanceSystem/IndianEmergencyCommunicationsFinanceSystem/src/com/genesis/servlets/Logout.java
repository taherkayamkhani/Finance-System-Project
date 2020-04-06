package com.genesis.servlets;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.BudgetCalculationBean;
import com.bean.ExpensesBean;

import com.genesis.logics.Budgetreport;

public class Logout extends HttpServlet {

	/**
	 * servlet  is   used for logout  of an  user
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter pw;
	Connection con;
	Statement stmt;
	ResultSet rs;
	public void init(ServletContext sc) throws SQLException{
	try {
			System.out.println(":i am in init:");
			ServletContext context=getServletContext();
			con=(Connection)context.getAttribute("connection");
             stmt=con.createStatement();
			//stmt=con.createStatement();
			System.out.println("connected to db");

		} catch (Exception e1) {
			//pw.println("<center><h1> error!!! Class not  found  Exception</h1></center>");
			//e1.printStackTrace();
		} 
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)  
	{
		HttpSession hs= req.getSession();
	//	hs.invalidate();
	
		try { 
			req.getRequestDispatcher("/demo.html").forward(req, res);		
String str="";

System.out.println("logging out");


} catch (NullPointerException e2) {
			e2.printStackTrace();
			System.out.println("problem");
		}

		catch (Exception e1) {
			e1.printStackTrace();
		}
		finally{
			hs.invalidate();
		}
	}

	
	public void finalize()  
	{
		pw.close();
		try {
			System.out.println("problem with  in EstimationBudget ");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}

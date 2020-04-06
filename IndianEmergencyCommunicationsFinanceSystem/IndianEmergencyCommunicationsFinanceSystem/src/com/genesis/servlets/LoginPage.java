/********************************************************************
 *                                                                  *
 *                                                                  *
 * this  servlet  is used for  login page                           *
 *                                                                  *
 *                                                                  *
 ********************************************************************/
package com.genesis.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bean.Loginbean;

import com.genesis.dal.DataBase;
import com.genesis.logics.Budgetreport;

import java.io.*;
import java.sql.*;

public class LoginPage extends HttpServlet {
	/**
	 * servlet  is  used for  login  of   an  user
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter pw;
	Connection con;
	Statement stmt;
	ResultSet rs;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		try { 
			 	pw = res.getWriter();
			Loginbean loginbean = new Loginbean();
			
			HttpSession hs = req.getSession();
			loginbean.setUsername(req.getParameter("username"));
			loginbean.setPassword(req.getParameter("password"));
			//loginbean.setUsertype(req.getParameter("usertype"));
               System.out.println("The username is "+req.getParameter("username"));
               System.out.println("The password is "+loginbean.getPassword());
         
              
				
		Budgetreport user= new Budgetreport();
		String str=user.login(loginbean);

if(str.equals("admin"))
{
	 hs.setAttribute("user1", loginbean.getUsername());
	
	System.out.println("out"+str);
	//pw.print(str);
	req.getRequestDispatcher("/index5.html").forward(req, res);
}
else if(str.equals("state")){
	 hs.setAttribute("user1", loginbean.getUsername());
	//pw.print(str);
	req.getRequestDispatcher("/state.html").forward(req, res);
}
else
{
	System.out.println("in else of login ");
	pw.print("<html><head><tittle></title><link rel='stylesheet' type='text/css' href='css/style5.css' /><link rel='stylesheet' type='text/css' href='css/style2.css' /><link rel='stylesheet' type='text/css' href='css/style12.css' /> <style>"	
		+"	@import url(http://fonts.googleapis.com/css?family=Montserrat:400,700|Handlee);"
		+"	body {"
			+"	background: #eedfcc url(kk123.jpg) ;"
		+"		-webkit-background-size: cover;"
		+"		-moz-background-size: cover;"
		+"		background-size: cover;}"
		+"	.container > header h1,"
	+"		.container > header h2 {"
		+"		color: #fff;"
+"				text-shadow: 0 1px 1px rgba(0,0,0,0.5);"
			+"}"
	+"	</style> <script type='text/javascript'>alert('failed to  login'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/demo.html';</script> </head><body background='kk123.jpg'></body></html>");
}
System.out.print(str);
		} catch (NullPointerException e2) {
			e2.printStackTrace();
			System.out.println("problem");
		}

		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void finalize()  
	{
		pw.close();
		try {
			System.out.println("problem with ");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}









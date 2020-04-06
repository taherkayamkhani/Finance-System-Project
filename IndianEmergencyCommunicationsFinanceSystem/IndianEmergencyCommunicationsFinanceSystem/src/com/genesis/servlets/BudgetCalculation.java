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


import com.bean.EstimatedBudgetbean;
import com.bean.ExpensesBean;
import com.genesis.logics.Budgetreport;

public class BudgetCalculation extends HttpServlet {

	/**
	 * this  servlet  is  used  for   calculating  the  budget  for  all quarterlies  of  an state 
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
		try { 
			

			pw = res.getWriter();
	BudgetCalculationBean ex= new BudgetCalculationBean();
	HttpSession hs= req.getSession();
	ex.setStatename(req.getParameter("state"));
	ex.setFinyear(req.getParameter("finyear"));

		Budgetreport user= new Budgetreport();
		String str= user.budget(ex);
		
		
		

		

if(str.equals("Already")){
	
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
		+"	</style> <script type='text/javascript'>alert('sorry  !!!!! now  u  cant  update!!!!'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");
	
	/*res.setContentType("text/html");
	pw.println("<html><script>alert('Profile is Updated');</script></html>");*/
	/*RequestDispatcher RD = getServletContext().getRequestDispatcher("/CandidateUpdate");
	RD.forward(req, res);*/
System.out.println(str);		
		


}
else if(str.equals("inserted")){
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
		+"	</style> <script type='text/javascript'>alert('Budget Report  Submitted'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");
	
	
	/*RequestDispatcher RD = getServletContext().getRequestDispatcher("/CandidateUpdate");
	RD.forward(req, res);*/
	/*req.getRequestDispatcher("/CandidateUpdate").forward(req, res);*/
	
}
else if(str.equals("notEstimatedYet")){
	
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
		+"	</style> <script type='text/javascript'>alert('Budget For this state has  not  been Estimated!!!!'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");
	
	/*res.setContentType("text/html");
	pw.println("<html><script>alert('Profile is Updated');</script></html>");*/
	/*RequestDispatcher RD = getServletContext().getRequestDispatcher("/CandidateUpdate");
	RD.forward(req, res);*/
System.out.println(str);		
		


}
		
else 
{
	pw.print(str);
	
	
	/*RequestDispatcher RD = getServletContext().getRequestDispatcher("/CandidateUpdate");
	RD.forward(req, res);*/
	/*req.getRequestDispatcher("/CandidateUpdate").forward(req, res);*/
	System.out.println(str);
}
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
			System.out.println("problem with  in EstimationBudget ");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}

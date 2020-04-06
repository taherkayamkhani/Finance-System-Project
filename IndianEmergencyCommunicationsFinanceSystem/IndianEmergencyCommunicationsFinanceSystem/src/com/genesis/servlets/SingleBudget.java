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

public class SingleBudget extends HttpServlet {

	/**
	 * servlet   is  used  for  display  the state  budget   at  state side as well as admin side 
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

			String str="";
			pw = res.getWriter();
			ExpensesBean ex= new ExpensesBean();
			ex.setFinyear(req.getParameter("finyear"));

			HttpSession hs= req.getSession();
			//hs.setAttribute("state", req.getParameter("state"));
			String name= hs.getAttribute("user1").toString();
			hs.setAttribute("year1", req.getParameter("finyear"));
			System.out.println(ex.getStatename());
			Budgetreport user= new Budgetreport();
			if(name.equals("india"))
			{
				ex.setStatename(req.getParameter("state"));
				System.out.println("in  india");
				str= user.viewsingleBudget(ex);
			}
			else{
				ex.setStatename(hs.getAttribute("user1").toString());
				str= user.singleBudget(ex);
			}	

			if(str.equals("noBudget")){
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
						+"	</style> <script type='text/javascript'>alert('there is  no  expenditure   details  For  "+ex.getStatename()+"  and  financial  year "+ex.getFinyear()+"'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/index5.html';</script> </head><body background='kk123.jpg'></body></html>");

			}else if(str.equals("noBudget1")){
				pw.print("<html><head><tittle></title><style>"	
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
						+"	</style> <script type='text/javascript'>alert('there is  no  expenditure   details  For  "+ex.getStatename()+"  and  financial  year "+ex.getFinyear()+"'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");

			}
			else
				pw.print(str);



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

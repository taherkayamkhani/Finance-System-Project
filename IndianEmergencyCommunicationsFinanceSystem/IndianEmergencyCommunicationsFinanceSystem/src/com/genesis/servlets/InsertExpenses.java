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

public class InsertExpenses extends HttpServlet {

	/**
	 * Servlet  is  used  for  inserting  the  expenditure  budget  of  state
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
		ExpensesBean es=  new ExpensesBean();		
	HttpSession hs= req.getSession();
	es.setBuilding_maintaince(req.getParameter("building"));
	es.setContractServices(req.getParameter("contract"));
	es.setDues(req.getParameter("dues"));
	es.setEquiment_replacement(req.getParameter("Equipment"));
	es.setFringe_benefits(req.getParameter("fringe"));
	es.setIndirect_Expenses(req.getParameter("indirect"));
	es.setOperations(req.getParameter("opertaion"));
	es.setPublic_education(req.getParameter("public_education"));
	es.setSalary(req.getParameter("salary"));
	es.setSupplies(req.getParameter("supplies"));
es.setTraining(req.getParameter("training"));
es.setTravel(req.getParameter("travel"));
	
	es.setFinyear(req.getParameter("finyear"));
	es.setQuaterly(req.getParameter("qtrly"));
es.setStatename(hs.getAttribute("user1").toString());	
System.out.println("frienge::"+es.getFringe_benefits());
System.out.println("frienge::"+req.getParameter("fringe"));		
Budgetreport user= new Budgetreport();
BudgetCalculationBean  ex=  new BudgetCalculationBean();
ex.setStatename(hs.getAttribute("user1").toString());
ex.setFinyear(req.getParameter("finyear"));
ex.setFinyear2(hs.getAttribute("year1").toString());
es.setFinyear2(hs.getAttribute("year1").toString());

System.out.println("cal"+ex.getFinyear());
System.out.println("cal"+ex.getStatename());
String  str1="";
String str= user.insertExpenses(es);
if(str.equals("inserted")){
	 str1=user.budget(ex);
}
System.out.println("Expense str="+str);
System.out.println("Expense str1="+str1);		

if(str.equals("exists")){
	
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
		+"	</style> <script type='text/javascript'>alert('sorry    Expenditure  report  is  present .. If  you  want to  edit then go to Edit Section .!!!!'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");
	
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
		+"	</style> <script type='text/javascript'>alert('Budget  Report  Submitted'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");
	
	
	/*RequestDispatcher RD = getServletContext().getRequestDispatcher("/CandidateUpdate");
	RD.forward(req, res);*/
	/*req.getRequestDispatcher("/CandidateUpdate").forward(req, res);*/
	
}
else if(str.equals("uploaded"))
{
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
		+"	</style> <script type='text/javascript'>alert('Budget Report  Updated'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");
	
	
	/*RequestDispatcher RD = getServletContext().getRequestDispatcher("/CandidateUpdate");
	RD.forward(req, res);*/
	/*req.getRequestDispatcher("/CandidateUpdate").forward(req, res);*/
	System.out.println(str);
}
if(str.equals("notEstimatedYet"))
{
	pw.print("<html><head><tittle></title><link rel='stylesheet' type='text/css' href='css/style5.css' /><link rel='stylesheet' type='text/css' href='css/style2.css' /><link rel='stylesheet' type='text/css' href='css/style12.css' /> <style>"	
			+"	@import url(http://fonts.googleapis.com/css?family=Montserrat:400,700|Handlee);"
			+"	body {"
				+"	background: #eedfcc url(images/ipad-wallpaper-blue-gradient.png) ;"
			+"		-webkit-background-size: cover;"
			+"		-moz-background-size: cover;"
			+"		background-size: cover;}"
			+"	.container > header h1,"
		+"		.container > header h2 {"
			+"		color: #fff;"
	+"				text-shadow: 0 1px 1px rgba(0,0,0,0.5);"
				+"}"
		+"	</style> <script type='text/javascript'>alert('Estimation  has  not  been  declared for "+ex.getFinyear()+"  year   for"+ex.getStatename()+"'); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='images/ipad-wallpaper-blue-gradient.png.jpg'></body></html>");
	
	
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

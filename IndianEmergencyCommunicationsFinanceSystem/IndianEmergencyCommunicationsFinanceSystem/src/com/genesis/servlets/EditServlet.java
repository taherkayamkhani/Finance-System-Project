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
import com.genesis.dal.DataBase;
import com.genesis.logics.Budgetreport;

public class EditServlet extends HttpServlet {

	/**
	 * this  servlet  is used  for  creating  the Edit  Expenses  page  in html
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter pw;
	Connection con;
	Statement st;
	ResultSet rs;
	public void init(ServletContext sc) throws SQLException{
	try {
			System.out.println(":i am in init:");
			ServletContext context=getServletContext();
			con=(Connection)context.getAttribute("connection");
             st=con.createStatement();
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
			
String str1="";
			pw = res.getWriter();
	EstimatedBudgetbean ex= new EstimatedBudgetbean();
	HttpSession hs= req.getSession();
	String name= hs.getAttribute("user1").toString();
	String year= hs.getAttribute("year1").toString();
	String qtrly= req.getParameter("qtrly");
	hs.setAttribute("qtrly1", qtrly);
	con=DataBase.getConnection();
	
	Statement st=con.createStatement();
	int count=0;
System.out.println("select  salary,frienge_benefits,travel,due,supplies,contract,indirect_expenses,operations,public_educations,Equipment,training,status from expenses where  Statename='"+name+"' and finyear='"+year+"'and  qtrly='"+qtrly+"'");
	rs=st.executeQuery(" select  salary,frienge_benefits,travel,due,supplies,contract,indirect_expenses,operations,public_educations,Equipment,training,status from expenses where  Statename='"+name+"' and finyear='"+year+"'and  qtrly='"+qtrly+"'");
		
	
	
	 
	if(rs.next())
	{
	
		
		if(rs.getString(12).equals("inserted"))	

		{
System.out.println(rs.getString(12));

	 str1="<!DOCTYPE html>"+
"<html>"+
"<head>"+
	"<meta charset='utf-8'><title>IndianEmergencyCommunicationsFinanceSystem</title>"+
	"<link rel='stylesheet' media='screen' href='styles.css' >"+
	  
"</head><body background='kk123.jpg'><header>"+
            "<img src='iecs.JPG' width='100%' height='80px' style='position: 0px 0 0 0 '> </header>"+
"<form class='contact_form' action='ExpensesServlet' method='get' name='contact_form'>"+
 "<font color='black'><ul >"+
    "<br> <li>"+
             "<h2>Edit Budget</h2>"+
             "<span class='required_notification'>* Denotes Required Field</span></li>"+
       
             
        
         
       
       " <li> " +
       " <label for='name'>Salaries:</label>" +
 
       " <input type='text' name='salary' id='salary' placeholder='Salaries' value='"+rs.getString(1)+"' pattern='[0-9]+.?[0-9]{0,2}'/>" +
       "</li>" +
       "<li>" +
       "<label for='name'>Frienge Benefits:</label>" +
       "    <input type='text' name='fringe' id='fringe' placeholder='frienge benefits' value='"+rs.getString(2)+"' pattern='[0-9]+.?[0-9]{0,2}' />" +
       "       </li>" +
       " <li>" +
       "<label for='name'>Travel Charges:</label>" +
       "<input type='text' name='travel' id='travel' placeholder='Travel charges' value='"+rs.getString(3)+"' pattern='[0-9]+.?[0-9]{0,2}'/>   </li>" +
       "   <li>" +
       "<label for='name'>Due:</label> <input type='text' name='dues' id='dues' value='"+rs.getString(4)+"' placeholder='Due'  pattern='[0-9]+.?[0-9]{0,2}' />" +
       "</li>" +
       "<li>" +
       "<label for='name'>Supplies:</label>" +
       "<input type='text' name='supplies' id='supplies' placeholder='Supplies' value='"+rs.getString(5)+"' pattern='[0-9]+.?[0-9]{0,2}' /></li>" +
       "<li>" +
       "<label for='name'>Contract:</label>" +
       "<input type='text' name='contract' id='contract' placeholder='contract' value='"+rs.getString(6)+"' pattern='[0-9]+.?[0-9]{0,2}' />" +
       "</li>" +
       "<li> " +
       "<label for='name'>indirect expenses:</label><input type='text' value='"+rs.getString(7)+"' name='indirect' id='indirect' placeholder='indirect_expenses'  pattern='[0-9]+.?[0-9]{0,2}' /></li>" +
       "        <li>" +
       "<label for='name'>public_educations:</label>" +
       "<input type='text' name='public_education' id='public_education' value='"+rs.getString(9)+"' placeholder='public_educations'  pattern='[0-9]+.?[0-9]{0,2}' />" +
       "</li>" +
       "<li>" +
       "<label for='name'>Equipment:</label>" +
       "<input type='text' name='Equipment' id='Equipment' placeholder='Equipment' value='"+rs.getString(10)+"' pattern='[0-9]+.?[0-9]{0,2}' />" +
       "</li>" +
       "<li>" +
       "<label for='name'>Training:</label>" +
       "<input type='text' name='training' id='training' placeholder='Training charges' value='"+rs.getString(11)+"' pattern='[0-9]+.?[0-9]{0,2}' />" +
       "</li>" +
       "<li>" +
       "<label for='name'>operations:</label>" +
       "<input type='text' name='opertaion' id='opertaion' placeholder='operations' value='"+rs.getString(8)+"' pattern='[0-9]+.?[0-9]{0,2}' />" +
       "</li>" +
       "<li>" +
       "<a href='state.html'> " +
       "<button class='submit' type='button'>back</button></a>" +
       " <button class='submit1' type='submit'>Submit Form</button>" +
       "</li>" +
       "<br>" +
       "<br>   " +
       "</ul>   " +
       " </font>" +
       "</form>" +
       "</body>" +
       "</html>" ;
       
	}  
	 
 
	}
	else
	{
		str1="not";
	}   
System.out.println(str1);
	
if(str1.equals("not"))
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
			+"	</style> <script type='text/javascript'>alert('you  cant  edit  this  quaterly  go  to   give  budget  expenditure for   inserting  the  budget...'  ); window.location = 'http://localhost:9090/IndianEmergencyCommunicationsFinanceSystem/state.html';</script> </head><body background='kk123.jpg'></body></html>");
		
	}       

	else
	{
	pw.print(str1);
	}	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

DataBase.close(rs, st, con);
		
		
	

		


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

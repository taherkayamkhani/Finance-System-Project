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

public class EditEstimation extends HttpServlet {

	/**
	 * this  servlet is  used  to  create  a  Edit  Estimation  Page  in  html
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
	
	con=DataBase.getConnection();
	
	Statement st=con.createStatement();
	int count=0;
	rs=st.executeQuery("select username	from users where usertype='state'");{
		while(rs.next())
		{
		count++;
			System.out.println(count);
		}
	}
	
	 String [] state= new  String[count];
	 String[] state1= new String[count];
	 int a=0;
	rs=st.executeQuery("select username,statename	from users where usertype='state' ");
	
	while(rs.next())
	{
		
		
	state[a]=rs.getString(1);
	state1[a]=rs.getString(2);
		a++;
		System.out.println(rs.getString(1));
	
				}


	 str1="<html><head><link href='css12/layout.css' rel='stylesheet' type='text/css' />"+
        "<link href='css12/menu.css' rel='stylesheet' type='text/css' /></head><body background='kk123.jpg'>" +
        "<header>"
			 
            +"<img src='iecs.JPG' width='100%' height='80px' style='position: 0px 0 0 0 '>"+
            
       " </header><br><br><br><br><br><br>" +
       "<form class='form1' action='EstimationBudget' method='get' id='f1'>"
+"<br><br><br><br><br><br><br><br>" +
"<center>" +
"<ul>" +
"<br>" +
"<li>" +
"<select name='state' id='state' required>";
			for(int i=0;i<count;i++)
			{		
 str1 += "<option value="+state[i]+">"+state1[i]+"</option>";
			}
        
         
       
str1+="</select>" +
		"</li>" +
		"<li>" +
		"<input type='text'  id='finyear' name='finyear' class='usertext' size='15'   required pattern='[1-3	][0-9][0-9][0-9]' placeholder='Financial Year'/>"+
		"</li>"				
				+"<li>" +
				"<input type='text' id='budget' name='budget' class='usertext' size='15'   required pattern='[0-9]+.?[0-9]{0,2}' placeholder='Estimated Budget'/>" +
				"</li>"+					
			"<li>"	   
				  +" <button type='submit' name='submit'  >	" +
				  "<i   class='icon-arrow-right'></i>"+
				    	"<span>Submit</span>"+
				    "</button>" +
				    "</li>" +
				    "</ul>" +
				    "</center>" +
				    "</form>" +
				    "</body>" +
				    "</html>";
	
	
	
	
	pw.print(str1);
	 	
		
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

package com.genesis.logics;

import java.io.File;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.Converter;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.omg.CORBA.PUBLIC_MEMBER;






import com.bean.BudgetCalculationBean;
import com.bean.EstimatedBudgetbean;
import com.bean.ExpensesBean;

import com.bean.Loginbean;

import com.bean.RequestBean;

import com.genesis.dal.DataBase;
import com.genesis.dal.SendingEmail;
//import com.logic.DisplayResume;
//import com.logic.SendingUserEmail;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.sun.star.bridge.oleautomation.Date;












public class Budgetreport {
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	 String html="<html><head><tittle></title><link rel='stylesheet' type='text/css' href='styles.css' /><link rel='stylesheet' type='text/css' href='css/style2.css' /><link rel='stylesheet' type='text/css' href='css/style12.css' /> <style>"	
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
	+"	</style></head><body background='kk123.jpg'> ";
String  sorte="<body>"+

 "<div id='main'>"
  
   +" </header>";

	
String kk="<body>"
  +"<div id='main'>"
    +"<header>"
     +" <div id='logo'>"
        +"<div id='logo_text'>"
         +" <!-- class='logo_colour', allows you to change the colour of the text -->"
          +"<h1><a href='index.html'><span class='logo_colour'>Recruiters Commisioning</span></a></h1>"

        +"</div>"
      +"</div>"
      +"<nav><div id='menu_container'>"
         +" <ul class='sf-menu' id='nav'>"
            +"<li><a href='home.html'>recruitres</a></li>"
            +"<li><a href='candidate.html'>View candidate</a></li>"
           +"<li><a href='sort.html'>Sort candidate</a></li>"
           +" <li><a href='watch_list.html'>Watch list</a></li>"
           
              +"</ul>"
            +"</li>"
          
          +"</ul></div></nav>";
	 
	 
	 String bHtml="</body><html>";
	 String alink="<a href=";
	 String submit="<input type='submit'  value='submit' />";
	String button="<input type='button'   value='submit'onclick='validate()' />";
	String textbox="<input type='text' name='useraname' class='usertext' size='15' value= />";
	public String login(Loginbean login) 
 {
		//this  method  is  used  for  userlogin
		// TODO Auto-generated method stub
		
		String str="";
		
		
			try {
				con=DataBase.getConnection();
			
			st=con.createStatement();
			System.out.println("select  usertype,username,password from users where username='"+login.getUsername()+"' and Password='"+login.getPassword()+"'"); 
			rs=st.executeQuery("select  usertype,username,password from users where username='"+login.getUsername()+"' and Password='"+login.getPassword()+"' ");
			if(rs.next())
			{
				if(rs.getString(1).equals("admin"))
			
				{
					str+="admin";
				System.out.println("in can");
				}
				else 
				{	
					str+="state";
					System.out.println("in rec");
				}
				}
			else
			{
				str+="failed";
			System.out.println("failed to  login");
			}
			
			System.out.println("IN>>----------->"+str);
				DataBase.close(rs,st, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				str+="problem occured</root>";
				e.printStackTrace();
			}
			str+="";
			DataBase.close(rs, st, con);
			System.out.println(str);
			return str;
			}
	public String estimation(EstimatedBudgetbean es)
	{
	
		//this  method  is used  for  updating  the  estimation budget  at   admin(india) side
		String str="";
		try
		{
			System.out.println("in  method;");
			con=DataBase.getConnection();
			st=con.createStatement();
			
			
		 ResultSet rs= st.executeQuery("select * from estimatedbudget where  statename='"+es.getStatename()+"' and finyear='"+es.getFinyear()+"'");
			
		 if(rs.next())
				 {
				System.out.println("in  update  ");
				int c=st.executeUpdate("update estimatedbudget set Estimation ='"+es.getBudget()+"' where  statename='"+es.getStatename()+"' and finyear='"+es.getFinyear()+"'");
				 if(c==1)
				 {
					System.out.println("level2");
					 rs= st.executeQuery("SELECT Estimated_budget,total_expenses,percenatge_expenses,Remaining_amount FROM budget b where  statename='"+es.getStatename()+"' and financial_year='"+es.getFinyear()+"'");
					 if(rs.next()){
							System.out.println(rs.getString(1)+"level3"+rs.getString(2));
							
						 float estimation=Float.parseFloat(es.getBudget());
						 float tot=Float.parseFloat(rs.getString(2));
						 float per=(tot/estimation)*100.0f;
							float remaining= estimation-tot;
						 
							int c1=st.executeUpdate("update budget  set Estimated_budget='"+es.getBudget()+"',total_expenses='"+tot+"',percenatge_expenses='"+per+"',Remaining_amount='"+remaining+"' where  statename='"+es.getStatename()+"' and financial_year='"+es.getFinyear()+"'"); 
					 
					 {
					     System.out.println("level4");	 
					 }
					 }
					 System.out.println("successfully  uploaded" +es.getBudget()+"for  year"+es.getFinyear()+"for "+es.getStatename());
					 str+="budgetSubmitted";
				 }
				 }
			else
			{
				str+="Exists";
			}
							
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			str+="  Problem Occured ";
		}
		
		
		DataBase.close(rs, st, con);
		str+="";
		return str;
	}

	
	



public  String  editExpenses(ExpensesBean ex)
{
	//this  method  is  used  to   edit or  update the  expenditure budget  
	 SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");//dd/MM/yyyy
	    java.util.Date now = new java.util.Date();
	    String date = sdfDate.format(now);

	String str="";
	try
	{
		con=DataBase.getConnection();
		st=con.createStatement();
		

		System.out.println("SELECT Estimation FROM estimatedbudget where statename='"+ex.getStatename()+"'and  finyear='"+ex.getFinyear()+"'");
	rs=st.executeQuery("SELECT Estimation FROM estimatedbudget where statename='"+ex.getStatename()+"'and  finyear='"+ex.getFinyear()+"'");
	if(rs.next())	
	{
		
	
	System.out.println("else");
			rs=st.executeQuery("select date,statename,qtrly,finyear  from expenses where statename='"+ex.getStatename()+"'and  finyear='"+ex.getFinyear()+"' and qtrly='"+ex.getQuaterly()+"' ");
		if(rs.next())
				{
					String date1=rs.getString(1);
				System.out.println("datebase date:"+date1);
					//String format = "dd-MM-yyyy HH:MM:SS";
				String format = "dd-MM-yyyy";
					SimpleDateFormat sdf = new SimpleDateFormat(format);
					java.util.Date dateObj1 = sdf.parse(date1);
					java.util.Date dateObj2 = now;
					System.out.println("present date:"+now);
					long diff = dateObj2.getTime() - dateObj1.getTime();

					int diffDays =  (int) (diff / (24* 1000 * 60 * 60));
					System.out.println("Total  dayz  >----->"+diffDays);
					if(diffDays<=15)
					{
						
					
						if(ex.getSalary()!=""){
							System.out.println("1");
							int c= st.executeUpdate("update expenses  set salary='"+ex.getSalary()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								System.out.println("SALARY updated");
								str="uploaded";
								c=0;
							}
						}
						if(ex.getFringe_benefits()!=""){
							int c= st.executeUpdate("update expenses  set frienge_benefits='"+ex.getFringe_benefits()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							System.out.println("1");
							if(c==1){
								str="uploaded";
								System.out.println("FRIENGE updated");
								c=0;
							}
						}
						if(ex.getTravel()!=""){
							System.out.println("1");
							int c= st.executeUpdate("update expenses  set travel='"+ex.getTravel()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("TRAVEL updated");
								c=0;
							}
						}
						if(ex.getDues()!=""){
							int c= st.executeUpdate("update expenses  set due='"+ex.getDues()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("DUE updated");
								c=0;
							}
						}
						if(ex.getSupplies()!=""){
							int c= st.executeUpdate("update expenses  set supplies='"+ex.getSupplies()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("SUPPLIES updated");
								c=0;
							}
						}
						if(ex.getContractServices()!=""){
							int c= st.executeUpdate("update expenses  set contract='"+ex.getContractServices()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("CONTRACT updated");
								c=0;
							}
						}
						if(ex.getIndirect_Expenses()!=""){
							int c= st.executeUpdate("update expenses  set indirect_expenses='"+ex.getIndirect_Expenses()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("INDIRECT updated");
								c=0;
							}
						}
						if(ex.getOperations()!=""){
							int c= st.executeUpdate("update expenses  set operations='"+ex.getOperations()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("OPERATIONS updated");
								c=0;
							}
						}
						if(ex.getPublic_education()!=""){
							int c= st.executeUpdate("update expenses  set public_educations='"+ex.getPublic_education()+"'where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("PUBLIC updated");
								c=0;
							}
						}
						if(ex.getEquiment_replacement()!=""){
							int c= st.executeUpdate("update expenses  set Equipment='"+ex.getEquiment_replacement()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								
								System.out.println("eQUIPMENT updated");
								c=0;
							}
						}
						
						if(ex.getTraining()!=""){
							int c= st.executeUpdate("update expenses  set training='"+ex.getTraining()+"' where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"'and qtrly='"+ex.getQuaterly()+"' ");
							if(c==1){
								str="uploaded";
								System.out.println("TRAINING updated");
								c=0;
							}
						}
							
						str="uploaded";
						 int  c2=st.executeUpdate("insert  into updates(date1,update1) values('"+date+"','"+ex.getStatename()+" has updated is  budget of  financial year "+ex.getFinyear()+"  for  "+ex.getQuaterly()+" on   "+date+" ')");
						System.out.println("data  uploaded");
					    
					
						
			}else{
				str+="daysOver";
				System.out.println("days  over");
			}
			
		}else{
			str+="notExists";
		}

		
		
	}else{
		str+="notEstimatedYet";
	}	
				
				}
			
			
			
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		str="Problem Occured ";
	}

	System.out.println(str);
	
	str+="";
	DataBase.close(rs, st, con);
	return str;
}


public  String  budget(BudgetCalculationBean ex)
{
	//this   method  is  used  for  calculating  the  total  budget expenditure of  4  quarterlies
	//as per  the  state.
	 SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS ");//dd/MM/yyyy
	    java.util.Date now = new java.util.Date();
	    String date = sdfDate.format(now);
float total=0;
	String str="";
	try
	{
		con=DataBase.getConnection();
		st=con.createStatement();
if(ex.getFinyear()==null){
	System.out.println("finyear2");
	System.out.println("SELECT Estimation FROM estimatedbudget where statename='"+ex.getStatename()+"' and  finyear='"+ex.getFinyear2()+"';");		
	rs= st.executeQuery("SELECT Estimation FROM estimatedbudget where statename='"+ex.getStatename()+"' and  finyear='"+ex.getFinyear2()+"';");
	if(rs.next())
	{
	System.out.println(" total  Estimation   is  :"+rs.getString(1));
	float estimation= Float.parseFloat(rs.getString(1));
		for(int i=1;i<5;i++)
		{
			System.out.println(" finyear"+ex.getFinyear2()+" Quaterly"+i);
	rs=st.executeQuery("select salary,frienge_benefits,travel,due,supplies,contract,indirect_expenses,operations,public_educations,Equipment,training  from expenses where statename='"+ex.getStatename()+"'and  finyear='"+ex.getFinyear2()+"' and qtrly='"+i+"' ");
	if(rs.next()){	
	for(int j=1;j<12;j++){
		total+=Float.parseFloat(rs.getString(j));
		System.out.println("in tot"+rs.getString(j));
		}
	}
	else{
		total+=0;	
		}
		}
	float per=(total/estimation)*100.0f;
	float remaining= estimation-total;
	ResultSet rs=   st.executeQuery("select  * from  budget where  financial_year='"+ex.getFinyear2()+"' and  statename='"+ex.getStatename()+"'");
	if(rs.next())
	{
		System.out.println("update budget set total_expenses='"+total+"',percenatge_expenses='"+per+"',Remaining_amount='"+remaining+"' where  financial_year='"+ex.getFinyear2()+"' and  statename='"+ex.getStatename()+"'");
		int  c1=st.executeUpdate("update budget set total_expenses='"+total+"',percenatge_expenses='"+per+"',Remaining_amount='"+remaining+"' where  financial_year='"+ex.getFinyear2()+"' and  statename='"+ex.getStatename()+"'");
		if(c1==1){
		str+="inserted";
		System.out.println("inserted");
		int  c2=st.executeUpdate("insert  into updates(date1,update1) values('"+date+"','"+ex.getStatename()+" has updated is  budget   on   "+date+"')");
		}	
	}
	else
	{
	int  c1=st.executeUpdate("insert  into budget(financial_year,Estimated_budget,total_expenses,statename,percenatge_expenses,Remaining_amount) values('"+ex.getFinyear2()+"','"+estimation+"','"+total+"','"+ex.getStatename()+"','"+per+"','"+remaining+"')");
	if(c1==1){
	str+="inserted";
	System.out.println("inserted");
	int  c2=st.executeUpdate("insert  into updates(date1,update1) values('"+date+"','"+ex.getStatename()+" has updated is  budget  of  "+ex.getFinyear2()+"  year  on   "+date+"')");
	}
	else{
		str+="error";
	}
		//to check given user name is exist or not
	}		

	}
	else{
		str+="notEstimatedYet";
	}

}		
else{
	System.out.println("SELECT Estimation FROM estimatedbudget where statename='"+ex.getStatename()+"' and  finyear='"+ex.getFinyear()+"';");		
	rs= st.executeQuery("SELECT Estimation FROM estimatedbudget where statename='"+ex.getStatename()+"' and  finyear='"+ex.getFinyear()+"';");
	if(rs.next())
	{
	System.out.println(" total  Estimation   is  :"+rs.getString(1));
	float estimation= Float.parseFloat(rs.getString(1));
		for(int i=1;i<5;i++)
		{
			System.out.println(" finyear"+ex.getFinyear()+" Quaterly"+i);
	rs=st.executeQuery("select salary,frienge_benefits,travel,due,supplies,contract,indirect_expenses,operations,public_educations,Equipment,training  from expenses where statename='"+ex.getStatename()+"'and finyear='"+ex.getFinyear()+"' and qtrly='"+i+"' ");
	if(rs.next()){	
	for(int j=1;j<12;j++){
		total+=Float.parseFloat(rs.getString(j));
		System.out.println("in tot"+rs.getString(j));
		}
	}
	else{
		total+=0;	
		}
		}
	float per=(total/estimation)*100.0f;
	float remaining= estimation-total;
	ResultSet rs=   st.executeQuery("select  * from  budget where financial_year='"+ex.getFinyear()+"' and  statename='"+ex.getStatename()+"'");
	if(rs.next())
	{
		System.out.println("update budget set total_expenses='"+total+"',percenatge_expenses='"+per+"',Remaining_amount='"+remaining+"' where financial_year='"+ex.getFinyear()+"'  and  statename='"+ex.getStatename()+"'");
		int  c1=st.executeUpdate("update budget set total_expenses='"+total+"',percenatge_expenses='"+per+"',Remaining_amount='"+remaining+"' where financial_year='"+ex.getFinyear()+"'  and  statename='"+ex.getStatename()+"'");
		if(c1==1){
		str+="inserted";
		System.out.println("inserted");
		int  c2=st.executeUpdate("insert  into updates(date1,update1) values('"+date+"','"+ex.getStatename()+" has updated is  budget   on   "+date+"')");
		}	
	}
	else
	{
	int  c1=st.executeUpdate("insert  into budget(financial_year,Estimated_budget,total_expenses,statename,percenatge_expenses,Remaining_amount) values('"+ex.getFinyear()+"','"+estimation+"','"+total+"','"+ex.getStatename()+"','"+per+"','"+remaining+"')");
	if(c1==1){
	str+="inserted";
	System.out.println("inserted");
	int  c2=st.executeUpdate("insert  into updates(date1,update1) values('"+date+"','"+ex.getStatename()+" has updated is  budget  of  "+ex.getFinyear()+"  year  on   "+date+"')");
	}
	else{
		str+="error";
	}
		//to check given user name is exist or not
	}		

	}
	else{
		str+="notEstimatedYet";
	}

}
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		str="Problem Occured ";
	}
	
	
	System.out.println("in budget:::output is:::"+str);
	str+="";
	DataBase.close(rs, st, con);
	return str;
}


public  String viewbudget(BudgetCalculationBean bd)
{
	//this  method  is  used for  displaying  the  overall budget at   admin side
	int  c=0;
	 String str="<html><body>" ;
	
	 
	 
	str+= "<center><table border=1 cellpadding='5' cellspacing='0'>";
	
	 try {
			con=DataBase.getConnection();
		
		st=con.createStatement();
		
		rs=st.executeQuery("select statename,financial_year,Estimated_budget,total_expenses,percenatge_expenses,Remaining_amount,fundRequested,fundGranted 	from budget order by statename ");
		if(rs.next())
		{
			
		str+="<tr><th>State Name</th><th>Financial Year</th><th>Estimated Budget</th><th>Total Expenses</th><th>Percenatge Expenditure %</th><th>Remaining Amount</th><th>Fund Requested</th><th>Fund  Granted</th></tr>";
		rs.beforeFirst();
		while(rs.next())
		{
			str+="<tr>";
			str+="<td>"+rs.getString(1)+"</a></td>";
			str+="<td>"+rs.getString(2)+"</td>";
			str+="<td>Rs "+rs.getString(3)+"</td>";
			str+="<td>Rs "+rs.getString(4)+"</td>";
			str+="<td>"+rs.getString(5)+" %</td>";
			str+="<td>Rs "+rs.getString(6)+"</td>";
			str+="<td>Rs   "+rs.getString(7)+"</td>";
			str+="<td>Rs "+rs.getString(8)+"</td>";
			
			
		str+="</tr>";
		
		
		
		
			 System.out.println(str);
		}
		 str+="</table></center>";
		
		 str+=bHtml;
		}
		else {
			 str+="<h1><font color='white'>Till now  Budget  Not  Calculated!!!! </font></h1>";
		}
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	str+="problem occured";
		}
	 
	
	System.out.println(str);
	System.out.println( " there are "+c+"candidate");
	DataBase.close(rs, st, con);
	 return str;
}


public  String  request(RequestBean re)
{
	// this  method  is  used  for   requesting  the  fund   by  state
	SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");//dd/MM/yyyy
    java.util.Date now = new java.util.Date();
    String date = sdfDate.format(now);
   float total=0;
	String str="";
	try
	{
		con=DataBase.getConnection();
		st=con.createStatement();
System.out.println("SELECT Estimation FROM estimatedbudget where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"';");		
rs= st.executeQuery("SELECT Estimation FROM estimatedbudget where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"';");
if(rs.next());
{



}	
re.setStatus("not granted")	;
rs= st.executeQuery("SELECT * FROM budget where statename='"+re.getStatename()+"' and  financial_year='"+re.getFinyear()+"';");
if(rs.next())
{




int  c1=st.executeUpdate("insert  into request(finyear,statename,budgetRequest,status) values('"+re.getFinyear()+"','"+re.getStatename()+"','"+re.getFund()+"','"+re.getStatus()+"')");
if(c1==1){
str+="inserted";
System.out.println("SELECT fundRequested FROM budget where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"';");		
rs= st.executeQuery("SELECT fundRequested FROM budget where statename='"+re.getStatename()+"' and  financial_year='"+re.getFinyear()+"';");
if(rs.next())
{
	
System.out.println(" total  Estimation   is  :"+rs.getString(1));
float fund= Float.parseFloat(rs.getString(1))+Float.parseFloat(re.getFund());
ResultSet	rs1= st.executeQuery("SELECT fundrequested FROM estimatedbudget where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"'");	
if(rs1.next())
{
	float fund1= Float.parseFloat(rs1.getString(1))+Float.parseFloat(re.getFund());
	int  c3=st.executeUpdate("update  estimatedbudget set fundrequested='"+fund1+"' where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"'");
	if(c3>0){

	System.out.println(" estimated funrequest  Updated");
	}
}
 
re.setStatus("granted")	;
System.out.println("update  budget set fundRequested='"+fund+"'where statename='"+re.getStatename()+"' and  financial_year='"+re.getFinyear()+"'");
int  c3=st.executeUpdate("update  budget set fundRequested='"+fund+"' where statename='"+re.getStatename()+"' and  financial_year='"+re.getFinyear()+"'");
if(c3>0){

System.out.println("Updated");
}
int  c2=st.executeUpdate("insert  into updates(date1,update1) values('"+date+"','"+re.getStatename()+" has Requested for budget of  RS  "+re.getFund()+"  for   Financial  year "+re.getFinyear()+" on  "+date+" ')");
System.out.println("inserted");
}
}
else{
	str+="error";
}
}else{
	str+="reportNotGenerated";
}

//to check given user name is exist or not
		
		
		
   }
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		str="Problem Occured ";
	}
	
	
	
	str+="";
	DataBase.close(rs, st, con);
	return str;
}

public  String pendingbudget(BudgetCalculationBean bd)
{int  c=0;
	
String str="";
	 //this   method is  used  for  displaying   the   pending  request    at  the  admin  side.
	
	
	 try {
			con=DataBase.getConnection();
		
		st=con.createStatement();
		
		rs=st.executeQuery("select statename,finyear,budgetRequest,id from request  where  status='not granted'");
		if(rs.next())
		{
			str="<html><head><link rel='stylesheet' media='screen' href='layout.css'></head><body>";
			 str+="<form action='/IndianEmergencyCommunicationsFinanceSystem/GrantRequest' method='get'>"	;
			str+= "<center><table border=1 cellpadding='5' cellspacing='0' padding='8px'>";	
		str+="<tr><th>State Name</th><th>Financial Year</th><th> Budget  Requested</th><th> Grant Request</th></tr>";
		rs.beforeFirst();
		while(rs.next())
		{
			str+="<tr>";
			str+="<td>"+rs.getString(1)+"</a></td>";
			str+="<td>"+rs.getString(2)+"</td>";
			str+="<td>Rs "+rs.getString(3)+"</td>";
			 str+="<td>   <input type='checkbox'  name='state' value= '"+rs.getString(4)+"'></td>";
			
			
		str+="</tr>";
		
		
		
		
			 System.out.println(str);
		}
		 str+="</table>";
		 str+=	" <button type='submit' name='submit'  >"+
				    "	<i   class='icon-arrow-right'></i>"+
				    "	<span>Submit</span>"+
				   " </button> </a></center>";
		 str+="</form>"+bHtml;
		
		}
		
		else {
			 str+="<h1><font color='white'>there are No Fund Request made by any State Recently</font></h1></body></html>";
		}
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	str+="problem occured";
		}
	
	
	System.out.println( " there are "+c+"candidate");
	DataBase.close(rs, st, con);
	 return str;
}
public  String  grantRequest(RequestBean re)
{
	//this  method  is   used  for granting  the request  made by any state  from admin side
	
	 
   float total=0;
	String str="";
	try
	{
		con=DataBase.getConnection();
		st=con.createStatement();
rs = st.executeQuery("select  statename,budgetRequest,finyear from request where id="+re.getId()+"");		
	if(rs.next()){
		re.setFinyear(rs.getString(3));
		re.setFund(rs.getString(2));
		re.setStatename(rs.getString(1));
		
	}	
		
	System.out.println("SELECT fundGranted FROM budget where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"';");		
	rs= st.executeQuery("SELECT fundGranted FROM budget where statename='"+re.getStatename()+"' and  financial_year='"+re.getFinyear()+"';");
	if(rs.next())
	{
	System.out.println(" total  Estimation   is  :"+rs.getString(1));	
    float fund= Float.parseFloat(rs.getString(1))+Float.parseFloat(re.getFund());
    rs= st.executeQuery("SELECT fundgranted FROM estimatedbudget where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"';");
   if(rs.next()) 
   {
	   System.out.println(" total  Estimation   is  :"+rs.getString(1));	
	    float fund1= Float.parseFloat(rs.getString(1))+Float.parseFloat(re.getFund());
	    int  c12=st.executeUpdate("update  estimatedbudget set fundgranted='"+fund1+"' where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"'");
		if(c12>0){
			System.out.println("dgk");
		
			
	    
    }
	re.setStatus("granted")	;
	System.out.println("update  budget set fundGranted='"+fund+"'where statename='"+re.getStatename()+"' and  financial_year='"+re.getFinyear()+"'");
	int  c1=st.executeUpdate("update  budget set fundGranted='"+fund+"' where statename='"+re.getStatename()+"' and  financial_year='"+re.getFinyear()+"'");
	if(c1>0){
		
		str+="Updated";
		int  c2=st.executeUpdate("update  request set status='"+re.getStatus()+"' where statename='"+re.getStatename()+"' and  finyear='"+re.getFinyear()+"' and  id='"+re.getId()+"'");
		if(c2>0){
			System.out.println("Status  changed");	
	
		}
		System.out.println("Updated");
}
else{
	str+="error";
}
	//to check given user name is exist or not
		
	}	
	}		
   }
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		str="Problem Occured ";
	}
	
	
	
	str+="";
	DataBase.close(rs, st, con);
	return str;
}
public String viewUpdates(){
	//this   method  is  used  for  displaying   the  updates  as per  latest  updates
	int  c=0;
String str="<html><body>";





try {
		con=DataBase.getConnection();
	
	st=con.createStatement();
	
	rs=st.executeQuery("SELECT update1 FROM updates  order by  id desc");
	if(rs.next())
	{
		str+= "<center><table border=1 cellpadding='5' cellspacing='0'>";	
	//str+="<tr><th></th><th>Financial Year</th><th>Estimated Budget</th><th>Total Expenses</th><th>Percenatge %</th><th>Remaining Amount</th><th>Fund Requested</th></tr>";
	rs.beforeFirst();
	while(rs.next())
	{
		
		str+="<tr>";
		str+="<td> <img src='images (1).jpg'width='20px' height='19px' >"+rs.getString(1)+"</td>";
		
		
		
	str+="</tr>";
	
	
	
	
		 System.out.println(str);
	}
	 str+="</table></center>";
	
	 str+=bHtml;
	}
	else {
		 str+="<h1><font color='white'>Till now no  updates have  been  made!!</font></h1></body></html>";
	}
	
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
str+="problem occured";
	}


System.out.println(str);

DataBase.close(rs, st, con);
return str;
}

@SuppressWarnings("null")
public  String singleBudget(ExpensesBean ex)
{
	//this  method  is  used   for  displaying   the   budget  expenditure  of a state  for  4 quarterlies  
	// at  state side for  a given  state  
	int  c=0;
	 String str="";
	
	
	 
	 float gtotal=0;
	String[][] can = new String[5][12];
	float[] tot = new float[12];
	
	 try {
			con=DataBase.getConnection();
		System.out.println(ex.getStatename()+">>--------->"+ex.getFinyear());
		st=con.createStatement();
	
		
		
			rs=st.executeQuery("SELECT status FROM expenses where  statename='"+ex.getStatename()+"' and (qtrly='1' or qtrly='2' or  qtrly='3' or qtrly='4') and finyear='"+ex.getFinyear()+"' ");
		
	
		if(rs.next())
		{
			str="<html><head><link rel='stylesheet' media='screen' href='styles.css' >"+
			 		"</head><body background='kk123.jpg'>";
			 str+=	"<header><img src='iecs.JPG' width='100%' height='80px</header><br><br><br><br>";
		for(int i=1;i<5;i++){
			rs=st.executeQuery("SELECT salary,frienge_benefits,travel,due,contract,supplies,indirect_expenses,operations,public_educations,Equipment,training,status FROM expenses where  statename='"+ex.getStatename()+"' and qtrly='"+i+"' and finyear='"+ex.getFinyear()+"' ");
		
		if(rs.next()){
			for(int j=1;j<12;j++){
				System.out.println(i+">>---------->"+rs.getString(j));
				can[i][j]=rs.getString(j);
				System.out.println("can=="+can[i][j]);
			}
			
		
		}
		else{
			for(int j=1;j<12;j++){
		
				can[i][j]="0.0";
				System.out.println("can=="+can[i][j]);
		}
		
	
		}
		}
for(int i=1;i<5;i++){
	for(int k=1;k<12;k++)
	{
		 tot[k]+=Float.parseFloat(can[i][k]);
	}
		}	
float[] round= new float[12];
for(int j=1;j<5;j++)		

{
	for(int i=1;i<12;i++)
{
	round[j]+=Float.parseFloat(can[j][i]);
}	
gtotal=0;
	
}
for(int i=1;i<12;i++)
{
	System.out.println(gtotal+"+");
	gtotal+=tot[i];
	System.out.println(gtotal);
}
		
str+= "<ul background='#fff'> <li><center><h1><font color='white'>State :"+ex.getStatename()+"                      Financial Year:"+ex.getFinyear()+"</font></center></li><l1><center><table border=1 cellpadding='5' cellspacing='0' font-color='white'>";	
str+="<tr><th>Category Name</th><th>quaterly 1</th><th>Quaterly 2</th><th>Quaterly 3</th><th>Quaterly 4</th> <th>Total</th></tr>";

	str+="<tr>";
	str+="<td>Salary</td><td>"+can[1][1]+"</td><td>"+can[2][1]+"</td><td>"+can[3][1]+"</td><td>"+can[4][1]+"</td><td>"+tot[1]+"</td></tr>";
	str+="<td>frienge_benefits</td><td>"+can[1][2]+"</td><td>"+can[2][2]+"</td><td>"+can[3][2]+"</td><td>"+can[4][2]+"</td><td>"+tot[2]+"</td></tr>";
	str+="<td>travel</td><td>"+can[1][3]+"</td><td>"+can[2][3]+"</td><td>"+can[3][3]+"</td><td>"+can[4][3]+"</td><td>"+tot[3]+"</td></tr>";
	str+="<td>due</td><td>"+can[1][4]+"</td><td>"+can[2][4]+"</td><td>"+can[3][4]+"</td><td>"+can[4][4]+"</td><td>"+tot[4]+"</td></tr>";
	str+="<td>contract</td><td>"+can[1][5]+"</td><td>"+can[2][5]+"</td><td>"+can[3][5]+"</td><td>"+can[4][5]+"</td><td>"+tot[5]+"</td></tr>";
	str+="<td>supplies</td><td>"+can[1][6]+"</td><td>"+can[2][6]+"</td><td>"+can[3][6]+"</td><td>"+can[4][6]+"</td><td>"+tot[6]+"</td></tr>";
	str+="<td>indirect_expenses</td><td>"+can[1][7]+"</td><td>"+can[2][7]+"</td><td>"+can[3][7]+"</td><td>"+can[4][7]+"</td><td>"+tot[7]+"</td></tr>";
	str+="<td>operations</td><td>"+can[1][8]+"</td><td>"+can[2][8]+"</td><td>"+can[3][8]+"</td><td>"+can[4][8]+"</td><td>"+tot[8]+"</td></tr>";
	str+="<td>public_educations</td><td>"+can[1][9]+"</td><td>"+can[2][9]+"</td><td>"+can[3][9]+"</td><td>"+can[4][9]+"</td><td>"+tot[9]+"</td></tr>";
	str+="<td>Equipment</td><td>"+can[1][10]+"</td><td>"+can[2][10]+"</td><td>"+can[3][10]+"</td><td>"+can[4][10]+"</td><td>"+tot[10]+"</td></tr>";
	str+="<td>training</td><td>"+can[1][11]+"</td><td>"+can[2][11]+"</td><td>"+can[3][11]+"</td><td>"+can[4][11]+"</td><td>"+tot[11]+"</td></tr>";
	str+="<td>total</td><td>"+round[1]+"</td><td>"+round[2]+"</td><td>"+round[3]+"</td><td>"+round[4]+"</td><td>"+gtotal+"</td></tr>";
	str+= "<td>Edit</td><td><a href='EditServlet?qtrly=1'><input type='button' value ='Edit'></td><td><a href='EditServlet?qtrly=2'><input type='button' value ='Edit'></td><td><a href='EditServlet?qtrly=3'><input type='button' value ='Edit'></td><td><a href='EditServlet?qtrly=4'><input type='button' value ='Edit'></td><td></td></tr>";
str+="</tr>";




	 System.out.println(str);

 str+="</table></li><a href='state.html'><input type='button' value ='Back'></a></li></ul></center>";
		
		 str+=bHtml;
				
		}
		else{
			str+="noBudget1";
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	str+="problem occured";
		}
	 
	
	System.out.println(str);
	System.out.println( " there are "+c+"candidate");
	DataBase.close(rs, st, con);
	 return str;
}


public  String viewsingleBudget(ExpensesBean ex)
{
	//this  method  is  used  to  display  the   expenditure  of a given state  at  the admin side   for  4 quarterlies  
	
	int  c=0;
String str="";
	
	 
	 float gtotal=0;
	String[][] can = new String[5][12];
	float[] tot = new float[12];
	
	 try {
			con=DataBase.getConnection();
		System.out.println(ex.getStatename()+">>--------->"+ex.getFinyear());
		st=con.createStatement();
		rs=st.executeQuery("SELECT status FROM expenses where  statename='"+ex.getStatename()+"' and (qtrly='1' or qtrly='2' or  qtrly='3' or qtrly='4') and finyear='"+ex.getFinyear()+"' ");
		
		if(rs.next())
		{
			str="<html><head><link rel='stylesheet' media='screen' href='styles.css' >"+
			 		"</head><body background='kk123.jpg'>";
				 str+=	"<header><img src='iecs.JPG' width='100%' height='80px</header><br><br><br>";
				
		for(int i=1;i<5;i++){
			rs=st.executeQuery("SELECT salary,frienge_benefits,travel,due,contract,supplies,indirect_expenses,operations,public_educations,Equipment,training FROM expenses where  statename='"+ex.getStatename()+"' and qtrly='"+i+"' and finyear='"+ex.getFinyear()+"' ");
		
		if(rs.next()){
			for(int j=1;j<12;j++){
				System.out.println(i+">>---------->"+rs.getString(j));
				can[i][j]=rs.getString(j);
				System.out.println("can=="+can[i][j]);
			}
			
		
		}
		else{
			for(int j=1;j<12;j++){
		
				can[i][j]="0";
				System.out.println("can=="+can[i][j]);
		}
		
	
		}
		}
for(int i=1;i<5;i++){
	for(int k=1;k<12;k++)
	{
		 tot[k]+=Float.parseFloat(can[i][k]);
	}
		}	
float[] round= new float[12];
for(int j=1;j<5;j++)		

{
	for(int i=1;i<12;i++)
{
	round[j]+=Float.parseFloat(can[j][i]);
}	
gtotal=0;
	
}
for(int i=1;i<12;i++)
{
	System.out.println(gtotal+"+");
	gtotal+=tot[i];
	System.out.println(gtotal);
}
		
		str+= "<ul background='#fff'> <li><center><h1><font color='white'>State :"+ex.getStatename()+"                      Financial Year:"+ex.getFinyear()+"</font></center></li><l1><center><table border=1 cellpadding='5' cellspacing='0' font-color='white'>";	
		str+="<tr><th>Category Name</th><th>quaterly 1</th><th>Quaterly 2</th><th>Quaterly 3</th><th>Quaterly 4</th> <th>Total</th></tr>";
	
			str+="<tr>";
			str+="<td>Salary</td><td>"+can[1][1]+"</td><td>"+can[2][1]+"</td><td>"+can[3][1]+"</td><td>"+can[4][1]+"</td><td>"+tot[1]+"</td></tr>";
			str+="<td>frienge_benefits</td><td>"+can[1][2]+"</td><td>"+can[2][2]+"</td><td>"+can[3][2]+"</td><td>"+can[4][2]+"</td><td>"+tot[2]+"</td></tr>";
			str+="<td>travel</td><td>"+can[1][3]+"</td><td>"+can[2][3]+"</td><td>"+can[3][3]+"</td><td>"+can[4][3]+"</td><td>"+tot[3]+"</td></tr>";
			str+="<td>due</td><td>"+can[1][4]+"</td><td>"+can[2][4]+"</td><td>"+can[3][4]+"</td><td>"+can[4][4]+"</td><td>"+tot[4]+"</td></tr>";
			str+="<td>contract</td><td>"+can[1][5]+"</td><td>"+can[2][5]+"</td><td>"+can[3][5]+"</td><td>"+can[4][5]+"</td><td>"+tot[5]+"</td></tr>";
			str+="<td>supplies</td><td>"+can[1][6]+"</td><td>"+can[2][6]+"</td><td>"+can[3][6]+"</td><td>"+can[4][6]+"</td><td>"+tot[6]+"</td></tr>";
			str+="<td>indirect_expenses</td><td>"+can[1][7]+"</td><td>"+can[2][7]+"</td><td>"+can[3][7]+"</td><td>"+can[4][7]+"</td><td>"+tot[7]+"</td></tr>";
			str+="<td>operations</td><td>"+can[1][8]+"</td><td>"+can[2][8]+"</td><td>"+can[3][8]+"</td><td>"+can[4][8]+"</td><td>"+tot[8]+"</td></tr>";
			str+="<td>public_educations</td><td>"+can[1][9]+"</td><td>"+can[2][9]+"</td><td>"+can[3][9]+"</td><td>"+can[4][9]+"</td><td>"+tot[9]+"</td></tr>";
			str+="<td>Equipment</td><td>"+can[1][10]+"</td><td>"+can[2][10]+"</td><td>"+can[3][10]+"</td><td>"+can[4][10]+"</td><td>"+tot[10]+"</td></tr>";
			str+="<td>training</td><td>"+can[1][11]+"</td><td>"+can[2][11]+"</td><td>"+can[3][11]+"</td><td>"+can[4][11]+"</td><td>"+tot[11]+"</td></tr>";
			//str+="<td>total</td><td>"+round[1]+"</td><td>"+round[2]+"</td><td>"+round[3]+"</td><td>"+round[4]+"</td><td>"+gtotal+"</td></tr>";
			
		str+="</tr>";
		
		
		
		
			 System.out.println(str);
		
		 str+="</table></li><l1><a href='index5.html'><input type='submit' value ='Back'></a></li></ul></center>";
		
		 str+=bHtml;
				
			
	 }else{
		 str+="noBudget";
	 }	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	str+="problem occured";
		}
	 
	
	System.out.println(str);
	System.out.println( " there are "+c+"candidate");
	DataBase.close(rs, st, con);
	 return str;
}
public  String viewEstimation(EstimatedBudgetbean es)
{
	//this  method  is  used   for  displaying  the  estimation  at  the   admin side
	int  c=0;
	 String str="<html><body>" ;
	
	 
	 
	str+= "<center><table border=1 cellpadding='5' cellspacing='0'>";
	
	 try {
			con=DataBase.getConnection();
		
		st=con.createStatement();
		
		rs=st.executeQuery("select finyear,Estimation,statename 	from estimatedbudget order by statename ");
		if(rs.next())
		{
			
		str+="<tr><th>State Name</th><th>Financial Year</th><th>Estimated Budget</th></tr>";
		rs.beforeFirst();
		while(rs.next())
		{
			str+="<tr>";
			str+="<td>"+rs.getString(3)+"</a></td>";
			str+="<td>"+rs.getString(1)+"</a></td>";
			str+="<td> Rs "+rs.getString(2)+"</td>";
			
			
			
		str+="</tr>";
		
		
		
		
			 System.out.println(str);
		}
		 str+="</table>" +
		 		"<l1><a href='EditEstimation'><button type='button' name='button'  width='30px' ><i   class='icon-arrow-right'></i>"+
				    	
				    	
"<span>Edit</span> </button> </a><a href='index5.html'><button type='button' name='button'  width='50px' ><i   class='icon-arrow-right'></i><span>Back</span> </button></a></center>";
		
		 str+=bHtml;
		}
		else {
			 str+="<h1><font color='white'>No Estimation For any States!!!! </font></h1>";
		}
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	str+="problem occured";
		}
	 
	
	System.out.println(str);
	System.out.println( " there are "+c+"candidate");
	DataBase.close(rs, st, con);
	 return str;	
}

public String viewStateEstimation(EstimatedBudgetbean ex){
	// this   method  is  used for  displaying  the   estimation at   state side
	int  c=0;
	 String str="<html><body>" ;
	
	 
	 
	
	
	 try {
			con=DataBase.getConnection();
		
		st=con.createStatement();
		
		rs=st.executeQuery("select finyear,Estimation,fundrequested,fundgranted	from estimatedbudget where statename='"+ex.getStatename()+"'  order by finyear ");
		if(rs.next())
		{
			
String granted ="";
			
	   str+= "<center><h3><font color='white'>StateName:"+ex.getStatename()+"</font></h3> <table border=1 cellpadding='5' cellspacing='0'>";	
		str+="<tr><th>Financial Year</th><th>Estimated Budget</th><th>Fund Requested</th><th>Fund Granted</th></tr>";
		rs.beforeFirst();
		while(rs.next())
		{
			System.out.println(rs.getString(4)+"  ajah    "+rs.getString(3));
			if((rs.getString(4).equals("0"))&&(rs.getString(3)=="0"))
			{
				System.out.println("0"); 
				granted="0";
			 } 
			else if((rs.getString(4).equals("0"))&&(!rs.getString(3).equals("0")))
			{
				System.out.println("1");
				granted="pending";
			}
				else{
			granted="RS "+rs.getString(4);
				}
			str+="<tr>";
			
			str+="<td>"+rs.getString(1)+"</a></td>";
			str+="<td> Rs "+rs.getString(2)+"</td>";
			str+="<td>"+rs.getString(3)+"</a></td>";
			str+="<td> "+granted+"</td>";
			
			
			
		str+="</tr>";
		
		
		
		
			 System.out.println(str);
		}
		 str+="</table></center>";
		
		 str+=bHtml;
		}
		else {
			 str+="<h1><font color='white'>No Estimation For any States!!!! </font></h1>";
		}
		
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	str+="problem occured";
		}
	 
	
	System.out.println(str);
	System.out.println( " there are "+c+"candidate");
	DataBase.close(rs, st, con);
	 return str;	


}
public String insertExpenses(ExpensesBean ex)
{
	  //this  method  is  used  for  insert   the  expenditure  of  state  in quarterly  wise
	 SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");//dd/MM/yyyy
	    java.util.Date now = new java.util.Date();
	    String date = sdfDate.format(now);

	String str="";
	try
	{
		con=DataBase.getConnection();
		st=con.createStatement();
		

	
		
	rs=st.executeQuery("SELECT Estimation FROM estimatedbudget where statename='"+ex.getStatename()+"'and  finyear='"+ex.getFinyear2()+"'");
	if(rs.next())	
	{
		
	
	System.out.println("else");
			rs=st.executeQuery("select date,statename,qtrly,finyear  from expenses where statename='"+ex.getStatename()+"'and  finyear='"+ex.getFinyear2()+"' and qtrly='"+ex.getQuaterly()+"' ");
if(rs.next())
				{
				
			str+="exists";
		}
		else{
			System.out.println("infinyear2");
			int  c1=st.executeUpdate("insert  into expenses(salary,finyear,qtrly,statename,date,frienge_benefits,travel,due,supplies,contract,indirect_expenses,operations,public_educations,Equipment,training,status) values('"+ex.getSalary()+"','"+ex.getFinyear()+"','"+ex.getQuaterly()+"','"+ex.getStatename()+"','"+date+"','"+ex.getFringe_benefits()+"','"+ex.getTravel()+"','"+ex.getDues()+"','"+ex.getSupplies()+"','"+ex.getContractServices()+"','"+ex.getIndirect_Expenses()+"','"+ex.getOperations()+"','"+ex.getPublic_education()+"','"+ex.getEquiment_replacement()+"','"+ex.getTraining()+"','inserted')");
	        if(c1==1){
		   str+="inserted";
		   int  c2=st.executeUpdate("insert  into updates(date1,update1) values('"+date+"','"+ex.getStatename()+" has updated is  budget of  financial year "+ex.getFinyear()+"  for  "+ex.getQuaterly()+" quatrly on  "+date+" ')");
		   System.out.println("inserted");
		}

		
		}	
	}
	}	
			
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		str="Problem Occured ";
	}

	System.out.println(str);
	
	str+="";
	DataBase.close(rs, st, con);
	return str;
}
public String insertEstimation(EstimatedBudgetbean es)
{//this method  is  used for  inserting  the   estimation  of   state   from   admin side

	String str="";
	try
	{
		System.out.println("in  method;");
		con=DataBase.getConnection();
		st=con.createStatement();
		
		
	 ResultSet rs= st.executeQuery("select * from estimatedbudget where  statename='"+es.getStatename()+"' and finyear='"+es.getFinyear()+"'");
		
	 if(rs.next())
			 {
			 str+="notExists";
			 }
		else
		{
			int c=st.executeUpdate("insert into estimatedbudget (finyear,Estimation,statename) values('"+es.getFinyear()+"','"+es.getBudget()+"','"+es.getStatename()+"')");
		if(c>0)
		{
		str+="uploaded";
			System.out.println("Successfully  registered");	
		}
		}
						
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		str+="  Problem Occured ";
	}
	
	
	DataBase.close(rs, st, con);
	str+="";
	return str;
}




}


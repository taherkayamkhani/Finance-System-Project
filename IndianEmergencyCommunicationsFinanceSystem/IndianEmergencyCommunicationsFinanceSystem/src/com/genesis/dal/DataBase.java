package com.genesis.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	
	private static final String DB_URL="jdbc:mysql://localhost:3306/financialbudget";
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e) {
		// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		Connection con=null;
		try
		{
			con=DriverManager.getConnection(DB_URL,"root","root");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(ResultSet rs,Statement st,Connection con)
	{
		try
		{
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(con!=null)
				con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void close(Statement st,Connection con)
	{
		try
		{
			if(st!=null)
				st.close();
			if(con!=null)
				con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

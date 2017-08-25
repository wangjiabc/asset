package com.asset.tool;

import java.sql.*;
import java.io.*;
import java.util.*;
import com.mysql.jdbc.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
public class TestDB {
	
	public static java.sql.Connection getConnection() throws SQLException,IOException{
		Properties props=new Properties();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		FileInputStream in=new FileInputStream("database.properties");
//		props.load(in);
//		in.close();
		
		String drivers=props.getProperty("jdbc.drivers");
		if(drivers!=null)
			System.setProperty("jdbc.drivers",drivers);
		/*
		String url=props.getProperty("jdbc.url");
		String username=props.getProperty("jdbc.username");
		String password=props.getProperty("jdbc.password");
		*/
		String url="jdbc:mysql://220.166.104.133:3306/voucher";
		String username="root";
		String password="123";
		return DriverManager.getConnection(url,username,password);
	}
}


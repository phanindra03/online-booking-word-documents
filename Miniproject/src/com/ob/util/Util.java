package com.ob.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
	
	
		
		
		public static Connection conn=null;
		public static Connection DbConnection() {
			String url="jdbc:oracle:thin:@10.219.34.3:1521/orcl";
			String user="trg216";
			String pass="training216";
			
			try {
				conn=DriverManager.getConnection(url,user,pass);
			} catch (SQLException e) {
				System.out.println("connection problem  "+e.getMessage());
				
			}
			
			return conn;
			
		}

	

	

}

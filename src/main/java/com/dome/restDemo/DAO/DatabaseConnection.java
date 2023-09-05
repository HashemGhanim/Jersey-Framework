package com.dome.restDemo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static Connection con;
	
	static {
		String url = "jdbc:mysql://localhost:3306/restdome";
        String user = "root";
        String pass = "171203Hf";
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url , user , pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("there is error");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return con;
	}

}

package com.ef.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String url = "jdbc:h2:tcp://localhost/~/logparser";
	private static String user = "sa";
	private static String passwd = "";

	public static Connection getConnection() {

		try {
			Class.forName("org.h2.Driver");

			return DriverManager.getConnection(url, user, passwd);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}

package fr.imie.gestionepicerie.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

	private static final ConnectionDAO instance = new ConnectionDAO();
	private Connection connection;
	/**
	 * methode pour se connecter a la base de donnée 
	 */
	public ConnectionDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://10.2.2.36/epicerie", "epicerie", "1234");
			System.out.println("connection ok");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return instance.connection;
	}

}

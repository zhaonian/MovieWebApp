/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author Luan
 */
public class DBConnection {

	private Connection dbConnection;

	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			dbConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/moviedb",
				"root", "password");

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Connection get_connection() {
		return dbConnection;
	}
}

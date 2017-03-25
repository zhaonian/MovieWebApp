/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import static java.lang.System.out;
import java.sql.*;
import java.util.logging.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Luan
 *
 *
 * Context initCtx = new InitialContext(); if (initCtx == null)
 * out.println("initCtx is NULL");
 *
 * Context envCtx = (Context) initCtx.lookup("java:comp/env"); if (envCtx ==
 * null) out.println("envCtx is NULL");
 *
 * // Look up our data source DataSource ds = (DataSource)
 * envCtx.lookup("jdbc/TestDB");
 *
 *
 */
public class DBConnection {

	private Connection dbConnection;

	public DBConnection() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			dbConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/moviedb",
				"root", "password");
//			Context initCtx = new InitialContext();
//			if (initCtx == null) {
//				out.println("initCtx is NULL");
//			}
//
//			Context envCtx = (Context) initCtx.lookup("java:comp/env");
//			if (envCtx == null) {
//				out.println("envCtx is NULL");
//			}
//
//			// Look up our data source
//			DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
//			
//			dbConnection = ds.getConnection();

		} catch (Exception ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Connection get_connection() {
		return dbConnection;
	}
	
	public void closeConnection() {
		try {
			if (dbConnection != null)
				dbConnection.close();
		} catch (SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

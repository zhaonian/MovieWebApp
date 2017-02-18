/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class UserVerification {

	private Connection dbConnection;

	public UserVerification(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ResultSet verifyUser(String email, String passwd) {
		ResultSet result = null;
		try {
			String select = "SELECT id FROM customers "
				+ "WHERE email = ? AND password = ?;";

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passwd);
			result = preparedStatement.executeQuery();

		} catch (SQLException ex) {
			Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public ResultSet verifyEmployee(String email, String passwd) {
		ResultSet result = null;
		try {
			String select = "SELECT * FROM employees "
				+ "WHERE email = ? AND password = ?;";

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passwd);
			result = preparedStatement.executeQuery();

		} catch (SQLException ex) {
			Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}

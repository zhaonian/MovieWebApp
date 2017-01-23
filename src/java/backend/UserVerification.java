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

	public boolean verifyUser(String email, String passwd) {
		try {
			String select = "SELECT id FROM customers "
				+ "WHERE email = ? AND password = ?;";

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passwd);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
}

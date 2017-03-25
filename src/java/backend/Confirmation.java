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
public class Confirmation {

	private Connection dbConnection;

	public Confirmation(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public int verifyCreditCard(String firstName, String lastName, String creditCard, String expDate) {
		String select = "SELECT * FROM creditcards WHERE first_name = ? "
			+ "AND last_name = ? "
			+ "AND (REPLACE(REPLACE(id, ' ', ''), '-', '') = ? "
			+ "OR id = ?) "
			+ "AND expiration = ?;";

		ResultSet result;

		try {
			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);

			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, creditCard);
			preparedStatement.setString(4, creditCard);
			preparedStatement.setString(5, expDate);

			result = preparedStatement.executeQuery();

			if (result.next()) {
				return 1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Confirmation.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class CartInsertion {

	private Connection dbConnection;

	public CartInsertion(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void insertMovieIntoCart(String customer_email, int movie_id, int num_copy) {
		try {
			String select1 = "INSERT INTO shopping_cart(customer_email, movie_id, num_copy) "
				+ "VALUES(?, ?, 1);";
			String select2 = "UPDATE shopping_cart "
				+ "SET num_copy=num_copy+1 WHERE customer_email=? AND movie_id=?;";

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select2);
			preparedStatement.setString(1, customer_email);
			preparedStatement.setInt(2, movie_id);

			if (preparedStatement.executeUpdate() == 0) {
				preparedStatement = dbConnection.prepareStatement(select1);
				preparedStatement.setString(1, customer_email);
				preparedStatement.setInt(2, movie_id);
				
				preparedStatement.executeUpdate();
			}

		} catch (SQLException ex) {
			Logger.getLogger(CartInsertion.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

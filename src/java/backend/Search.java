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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class Search {

	private Connection dbConnection;

	public Search(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ResultSet getMovieByTitle(String titlePattern) {
		ArrayList<Integer> arrayID = new ArrayList<>();
		ResultSet result = null;
		try {
			String select = "SELECT * FROM movies "
				+ "WHERE title LIKE ?;";

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setString(1, "%" + titlePattern + "%");
			result = preparedStatement.executeQuery();

		} catch (SQLException ex) {
			Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;

	}
}

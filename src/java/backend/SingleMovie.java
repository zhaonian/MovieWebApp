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
public class SingleMovie {

	private Connection dbConnection;

	public SingleMovie(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ResultSet getSingleMovie(int movieID) {
		ResultSet result = null;
		try {
			String select = "SELECT * FROM movies NATURAL JOIN stars NATURAL JOIN genres "
				+ "WHERE movies.id = ?;";

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setInt(1, movieID);

			result = preparedStatement.executeQuery();

		} catch (SQLException ex) {
			Logger.getLogger(SingleMovie.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}

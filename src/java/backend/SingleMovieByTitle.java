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
public class SingleMovieByTitle {

	private Connection dbConnection;

	public SingleMovieByTitle(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public backend.Movie getSingleMovieByTitle(String title) {
		backend.Movie movie = null;
		try {
			String select = "SELECT id FROM movies WHERE title = ?;";
			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			
			preparedStatement.setString(1, title);
			
			ResultSet result = preparedStatement.executeQuery();
			
			ArrayList<Integer> arrayIDs = new ArrayList<>();
			
			while (result.next()) {
				arrayIDs.add(result.getInt("id"));
			}

			backend.SingleMovie singleMovie = new backend.SingleMovie(dbConnection);
			movie = singleMovie.getSingleMovie(arrayIDs.get(0));
			
		} catch (SQLException ex) {
			Logger.getLogger(SingleMovieByTitle.class.getName()).log(Level.SEVERE, null, ex);
		}
		return movie;
	}
}

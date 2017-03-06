/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class FuzzySearch {

	private Connection dbConnection;

	public FuzzySearch(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ArrayList<backend.Movie> getMovieByFuzzySearch(String keywords) {
		ArrayList<backend.Movie> arrayMovies = new ArrayList<>();
		ResultSet result = null;
		try {
			String sql = "SELECT * FROM movies WHERE MATCH (title) AGAINST (? IN BOOLEAN MODE);";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, keywords + "*");

			result = preparedStatement.executeQuery();

			while (result.next()) {
				int id = result.getInt("year");
				String title = result.getString("title");

				backend.Movie movie = new backend.Movie();
				movie.setId(id);
				movie.setTitle(title);

				arrayMovies.add(movie);
			}

		} catch (Exception ex) {
			Logger.getLogger(SingleMovie.class.getName()).log(Level.SEVERE, null, ex);
		}
		return arrayMovies;
	}
}

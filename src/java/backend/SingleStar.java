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
public class SingleStar {

	private Connection dbConnection;

	public SingleStar(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public backend.Star getStarInfo(String firstName, String lastName) {
		backend.Star star = new backend.Star();
		try {
			String select = "SELECT * FROM movies, stars, stars_in_movies "
				+ "WHERE stars.id = stars_in_movies.star_id "
				+ "AND movies.id = stars_in_movies.movie_id "
				+ "AND first_name = ? "
				+ "AND last_name = ?;";

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);

			ResultSet result = preparedStatement.executeQuery();

			ArrayList<Movie> movies = new ArrayList<>();

			while (result.next()) {
				star.setId(result.getInt("stars.id"));
				star.setName(result.getString("stars.first_name") + " " + result.getString("stars.last_name"));
				star.setDob(result.getDate("dob"));
				star.setPicture_url(result.getString("photo_url"));
				String movieTitle = result.getString("movies.title");
				int movieID = result.getInt("movies.id");
				backend.Movie movie = new backend.Movie();
				movie.setId(movieID);
				movie.setTitle(movieTitle);
				movies.add(movie);
			}
			star.setMovieList(movies);

		} catch (SQLException ex) {
			Logger.getLogger(MoviesByWhat.class.getName()).log(Level.SEVERE, null, ex);
		}
		return star;
	}
}

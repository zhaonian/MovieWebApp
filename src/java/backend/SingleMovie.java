/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Array;
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
public class SingleMovie {

	private Connection dbConnection;

	public SingleMovie(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public backend.Movie getSingleMovie(int movieID) {
		backend.Movie movie = new backend.Movie();

		try {
			String select1 = "SELECT * FROM movies, stars, stars_in_movies "
				+ "WHERE movies.id = stars_in_movies.movie_id "
				+ "AND stars.id = stars_in_movies.star_id "
				+ "AND movies.id = ?;";
			
			String select2 = "SELECT * FROM movies, genres, genres_in_movies "
				+ "WHERE movies.id = genres_in_movies.movie_id "
				+ "AND genres.id = genres_in_movies.genre_id "
				+ "AND movies.id = ?;";

			PreparedStatement preparedStatementStar;
			preparedStatementStar = dbConnection.prepareStatement(select1);
			preparedStatementStar.setInt(1, movieID);
			ResultSet resultStar = preparedStatementStar.executeQuery();

			PreparedStatement preparedStatementGenre;
			preparedStatementGenre = dbConnection.prepareStatement(select2);
			preparedStatementGenre.setInt(1, movieID);
			ResultSet resultGenre = preparedStatementGenre.executeQuery();

			ArrayList<String> starNames = new ArrayList<>();
			
			while (resultStar.next()) {
				movie.setId(resultStar.getInt("id"));
				movie.setYear(resultStar.getInt("year"));
				movie.setTitle(resultStar.getString("title"));
				movie.setDirector(resultStar.getString("director"));
				movie.setTrailer(resultStar.getString("trailer_url"));
				movie.setBanner_url(resultStar.getString("banner_url"));
				starNames.add(resultStar.getString("first_name") + " " + resultStar.getString("last_name"));	
			}
			movie.setListStars(starNames);

			ArrayList<String> genres = new ArrayList<>();
			while (resultGenre.next()) {
				genres.add(resultGenre.getString("name"));
			}
			movie.setListGenres(genres);
			
		} catch (SQLException ex) {
			Logger.getLogger(SingleMovie.class.getName()).log(Level.SEVERE, null, ex);
		}
		return movie;
	}
}

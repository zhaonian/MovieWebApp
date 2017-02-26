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
public class XMLInsertion {

	private final Connection dbConnection;

	public XMLInsertion(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public int insertSingleMovie(String title, int year, String director) {

		String select = "INSERT INTO movies(id, title, year, director, banner_url, trailer_url) "
			+ "VALUES(null, ?, ?, ?, null, null);";
		int result = 0;
		try {
			PreparedStatement preparedStatementStar;
			preparedStatementStar = dbConnection.prepareStatement(select);

			preparedStatementStar.setString(1, title);
			preparedStatementStar.setInt(2, year);
			preparedStatementStar.setString(3, director);

			result = preparedStatementStar.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(XMLInsertion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public int insertGenre(String genreName) {
		String select = "INSERT INTO genres(id, name) "
			+ "VALUES(NULL, ?);";
		int result = 0;
		try {
			PreparedStatement preparedStatementStar;
			preparedStatementStar = dbConnection.prepareStatement(select);

			preparedStatementStar.setString(1, genreName);

			result = preparedStatementStar.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(XMLInsertion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public int insertGenresInMovies(String genreName, String movieTitle) {
		String select = "INSERT INTO genres_in_movies(genre_id, movie_id) "
			+ "VALUES((SELECT id FROM genres WHERE genres.name = ?), (SELECT id FROM movies WHERE movies.title = ?));";
		int result = 0;
		try {
			PreparedStatement preparedStatementStar;
			preparedStatementStar = dbConnection.prepareStatement(select);

			preparedStatementStar.setString(1, genreName);
			preparedStatementStar.setString(2, movieTitle);
			
			result = preparedStatementStar.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(XMLInsertion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;

	}

}

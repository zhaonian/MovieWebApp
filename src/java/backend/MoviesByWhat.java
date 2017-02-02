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
public class MoviesByWhat {

	private Connection dbConnection;

	public MoviesByWhat(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ResultSet getMoviesByGenre(String genre) {
		ResultSet result = null;
		try {
			String select = "SELECT * FROM movies WHERE genre = ?";
			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setString(1, genre);
			result = preparedStatement.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(MoviesByWhat.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	public ResultSet getMoviesByTitle(String c) {
		ResultSet result = null;
		try {
			String select = "SELECT * FROM movies WHERE title LIKE ?;";
			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			System.out.println("aaaaaaaaa---");
			preparedStatement.setString(1, c + "%");
			System.out.println(select + "---" + preparedStatement);
			result = preparedStatement.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(MoviesByWhat.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}

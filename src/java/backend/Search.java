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
import java.sql.Statement;
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

	public ResultSet getMovieByTitle(String titlePattern, String year, String director, String starFirstName, String starLastName) {
		long startTime = System.nanoTime();

		ArrayList<Integer> arrayID = new ArrayList<>();
		ResultSet result = null;
		try {
			String sql = "SELECT * FROM movies, stars, stars_in_movies "
				+ "WHERE movies.id = stars_in_movies.movie_id "
				+ "AND stars.id = stars_in_movies.star_id ";

			if (!titlePattern.equals("")) {
				sql = sql + "AND title LIKE '%" + titlePattern + "%' ";
			}
			if (!year.equals("")) {
				sql = sql + "AND movies.year = " + Integer.parseInt(year) + " ";
			}
			if (!director.equals("")) {
				sql = sql + "AND director LIKE '%" + director + "%' ";
			}
			if (!starFirstName.equals("")) {
				sql = sql + "AND stars.first_name LIKE '%" + starFirstName + "%' ";
			}
			if (!starLastName.equals("")) {
				sql = sql + "AND stars.last_name LIKE '%" + starLastName + "%' ";
			}
			sql = sql + ";";

			Statement select = dbConnection.createStatement();
			result = select.executeQuery(sql);

		} catch (SQLException ex) {
			Logger.getLogger(UserVerification.class.getName()).log(Level.SEVERE, null, ex);
		}
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime; //
//		System.out.println(elapsedTime);
		return result;
	}
}

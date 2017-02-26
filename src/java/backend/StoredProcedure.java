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
public class StoredProcedure {

	private Connection dbConnection;

	public StoredProcedure(Connection connection) {
		this.dbConnection = connection;
	}

	public int insertMovie(String title, int year, String director, String banner, String trailer, String firstName, String lastName, String genre) {

		String select = "CALL add_movie(?, ?, ?, ?, ?, ?, ?, ?, @return_value);";
		int result = 0;
		try {
			PreparedStatement preparedStatementStar;
			preparedStatementStar = dbConnection.prepareStatement(select);
			
			preparedStatementStar.setString(1, title);
			preparedStatementStar.setInt(2, year);
			preparedStatementStar.setString(3, director);
			preparedStatementStar.setString(4, banner);
			preparedStatementStar.setString(5, trailer);
			preparedStatementStar.setString(6, firstName);
			preparedStatementStar.setString(8, lastName);
			preparedStatementStar.setString(7, genre);
				
			result = preparedStatementStar.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(StoredProcedure.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}

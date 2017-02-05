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
public class Sortation {

	private Connection dbConnection;

	public Sortation(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ResultSet sortMovies(String sortByWhat) {
		ResultSet result = null;
		try {
			String select;
			if (sortByWhat.equals("title")) {
				select = "SELECT * FROM movies ORDER BY title;";
			} else {
				select = "SELECT * FROM movies ORDER BY year;";
			}

			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			result = preparedStatement.executeQuery();

		} catch (SQLException ex) {
			Logger.getLogger(Sortation.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}

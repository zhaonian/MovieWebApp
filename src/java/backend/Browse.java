/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class Browse {

	private Connection dbConnection;

	public Browse(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ResultSet getAllGenres() {
		ResultSet result = null;
		try {
			Statement select = dbConnection.createStatement();
			result = select.executeQuery("SELECT * FROM genres;");
		} catch (SQLException ex) {
			Logger.getLogger(Browse.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}

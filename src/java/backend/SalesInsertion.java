/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class SalesInsertion {

	private Connection dbConnection;

	public SalesInsertion(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void insertSales(int customer_id, int movie_id) {
		try {
			String select = "INSERT INTO sales(id, customer_id, movie_id, sale_date) "
				+ "VALUES(NULL, ?, ?, ?);";
			
			PreparedStatement preparedStatement;
			preparedStatement = dbConnection.prepareStatement(select);
			preparedStatement.setInt(1, customer_id);
			preparedStatement.setInt(2, movie_id);
			preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException ex) {
			Logger.getLogger(SalesInsertion.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}

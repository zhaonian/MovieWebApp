/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Luan
 */
public class Confirmation {

	private Connection dbConnection;

	public Confirmation(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public boolean verifyCreditCard(String firstName, String lastName, int creditCard, String expDate){
		String select = "";
		ResultSet result;
		PreparedStatement preparedStatement;
		preparedStatement = dbConnection.prepareStatement(select);
		preparedStatement.setString(creditCard, select);
		result = preparedStatement.executeQuery();
		if (result.next()) {
			return true;
		}
		return false;
	}
}

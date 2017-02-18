/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class DBMetaData {

	private Connection connection;

	public DBMetaData(Connection connection) {
		this.connection = connection;
	}

	// get the meta data for database
	public ArrayList<String> getMetaData() {

		ArrayList<String> infoArray = new ArrayList();
//		"SELECT distinct TABLE_NAME, COLUMN_NAME, COLUMN_TYPE  FROM COLUMNS WHERE TABLE_SCHEMA = \"moviedb\";"

		try {
			Statement select = connection.createStatement();
			DatabaseMetaData metaData = connection.getMetaData();
			String catalog = null;
			String schemaPattern = null;
			String tableNamePattern = null;
			String[] types = null;

			ResultSet result = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);

			while (result.next()) {
				String tableName = result.getString(3);
				if (tableName != null) {
					infoArray.add("###" + tableName + "###<br>");
				}

				ResultSet rs = select.executeQuery("SELECT * FROM " + tableName + ";");
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					if (rsmd.getColumnName(i) != null && rsmd.getColumnTypeName(i) != null) {
						infoArray.add(rsmd.getColumnName(i) + ": " + rsmd.getColumnTypeName(i) + "<br>");
					}
				}
				infoArray.add("-----------------------<br>");
			}
		} catch (SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return infoArray;
	}
}

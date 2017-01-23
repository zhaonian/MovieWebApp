/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Connection;

/**
 *
 * @author Luan
 */
public class Search {

	private Connection dbConnection;

	public Search(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public int getSearchResult(String title, int yearFrom, int yearTo, String director, String star) {
		
		
	}
}

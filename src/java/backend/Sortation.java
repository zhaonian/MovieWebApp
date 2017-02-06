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
import java.util.ArrayList;
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

	public ArrayList<backend.Movie> InsertionSort(String sortByWhat, ArrayList<backend.Movie> arrayMovies) {

		backend.Movie temp;

		if (sortByWhat.equals("titleAsc")) {
			for (int i = 1; i < arrayMovies.size(); i++) {
				for (int j = i; j > 0; j--) {
					if (arrayMovies.get(j).getTitle().compareTo(arrayMovies.get(j - 1).getTitle()) < 0) {
						temp = arrayMovies.get(j);
						arrayMovies.set(j, arrayMovies.get(j - 1));
						arrayMovies.set(j - 1, temp);
					}
				}
			}
		} else if (sortByWhat.equals("titleDesc")) {
			for (int i = 1; i < arrayMovies.size(); i++) {
				for (int j = i; j > 0; j--) {
					if (arrayMovies.get(j).getTitle().compareTo(arrayMovies.get(j - 1).getTitle()) >= 0) {
						temp = arrayMovies.get(j);
						arrayMovies.set(j, arrayMovies.get(j - 1));
						arrayMovies.set(j - 1, temp);
					}
				}
			}
		} else if (sortByWhat.equals("yearAsc")) {
			for (int i = 1; i < arrayMovies.size(); i++) {
				for (int j = i; j > 0; j--) {
					if (arrayMovies.get(j).getYear() - arrayMovies.get(j - 1).getYear() < 0) {
						temp = arrayMovies.get(j);
						arrayMovies.set(j, arrayMovies.get(j - 1));
						arrayMovies.set(j - 1, temp);
					}
				}
			}
		} else if (sortByWhat.equals("yearDesc")) {
			for (int i = 1; i < arrayMovies.size(); i++) {
				for (int j = i; j > 0; j--) {
					if (arrayMovies.get(j).getYear() - arrayMovies.get(j - 1).getYear() >= 0) {
						temp = arrayMovies.get(j);
						arrayMovies.set(j, arrayMovies.get(j - 1));
						arrayMovies.set(j - 1, temp);
					}
				}
			}
		}
		return arrayMovies;
	}

//	public ArrayList<backend.Movie> InsertionSortByYear(ArrayList<backend.Movie> arrayMovies) {
//
//		backend.Movie temp;
//		for (int i = 1; i < arrayMovies.size(); i++) {
//			for (int j = i; j > 0; j--) {
//				if (arrayMovies.get(j).getYear() - arrayMovies.get(j - 1).getYear() < 0) {
//					temp = arrayMovies.get(j);
//					arrayMovies.set(j, arrayMovies.get(j - 1));
//					arrayMovies.set(j - 1, temp);
//				}
//			}
//		}
//		return arrayMovies;
//	}

}

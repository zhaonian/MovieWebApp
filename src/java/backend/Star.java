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
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Luan
 */ 
//id, name, date of birth, picture of the star, and a list of (hyperlinked) movies
public class Star {
	
	private int id;
	private String name;
	private Date dob;
	private String picture_url;
	private ArrayList<Movie> movieList;
	
	public Star() {
		
	}
	
	public Star(int id, String name, Date dob, String picture_url, ArrayList<Movie> movieList) {
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.picture_url = picture_url;
		this.movieList = movieList;
	}

	// getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getDob() {
		return dob;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}
	
	
	// insert a star into the database
	public boolean insertStar(Connection connection, String namesFromWeb) {

		String[] names = getNames(namesFromWeb);
		String firstName = names[0];
		String lastName = names[1];

		int result = 0;
		try {
			String select1 = "INSERT INTO stars(id, first_name, last_name, dob, photo_url) "
				+ "VALUES(NULL, ?, ?, NULL, NULL);";

			PreparedStatement preparedStatement;

			preparedStatement = connection.prepareStatement(select1);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);

			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				return true;
			}
			
		} catch (SQLException ex) {
			System.out.println("SQL Error when insert star: " + ex);
		}
		return false;
	}
	
	// get single name
	public static String[] getNames(String namesFromWeb) {

		String[] names = new String[2];

		try {
			System.out.print("Name: ");
			String[] temp =namesFromWeb.split(" ");
			if (temp.length == 1) {
				names[0] = "";
				names[1] = Arrays.asList(temp).get(0);

			} else {
				names[0] = temp[0];
				names[1] = temp[1];
			}

		} catch (Exception e) {
			System.out.println("Invalid Name");
		}
		return names;
	}
}

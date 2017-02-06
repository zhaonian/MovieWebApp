/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.Date;
import java.util.ArrayList;

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
}

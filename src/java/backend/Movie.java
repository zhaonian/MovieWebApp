/*
 * To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package backend;

import java.sql.Array;

/**
 *
 * @author Luan
 */
//id, title, year, director, a list of genres (hyperlinked), poster, a list of stars (hyperlinked), and a link to its preview trailer
public class Movie {
	
	private int id;
	private int year;
	private String title;
	private String director;
	private Array listGenres;
	private String banner_url;
	private Array listStars;
	private String trailer;

	public Movie() {

	}

	public Movie(int id, int year, String title, String director, Array listGenres, String banner_url, Array listStars, String trailer) {
		this.id = id;
		this.year = year;
		this.title = title;
		this.director = director;
		this.listGenres = listGenres;
		this.banner_url = banner_url;
		this.listStars = listStars;
		this.trailer = trailer;
	}
	
	//getters
	public int getId() {
		return id;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}
	
	public Array getListGenres() {
		return listGenres;
	}
	
	public String getBanner_url() {
		return banner_url;
	}
	
	public Array getListStars() {
		return listStars;
	}
	
	public String getTrailer() {
		return trailer;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setListGenres(Array listGenres) {
		this.listGenres = listGenres;
	}
	
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	
	public void setListStars(Array listStars) {
		this.listStars = listStars;
	}
	
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
}
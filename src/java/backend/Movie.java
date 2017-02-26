/*
 * To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package backend;

import java.util.ArrayList;

/**
 *
 * @author Luan
 */
//id, title, year, director, a list of genres (hyperlinked), poster, a list of stars (hyperlinked), and a link to its preview trailer
public class Movie {
	
	private int id;
	private int year = 0;
	private String title = "";
	private String director = "";
	private ArrayList<String> listGenres = new ArrayList<>();
	private String banner_url;
	private ArrayList<String> listStars = new ArrayList<>();
	private String trailer;

	public Movie() {

	}

	public Movie(int id, int year, String title, String director, ArrayList<String> listGenres, String banner_url, ArrayList<String> listStars, String trailer) {
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
	
	public ArrayList<String> getListGenres() {
		return listGenres;
	}
	
	public String getBanner_url() {
		return banner_url;
	}
	
	public ArrayList<String> getListStars() {
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
	
	public void setListGenres(ArrayList<String> listGenres) {
		this.listGenres = listGenres;
	}
	
	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}
	
	public void setListStars(ArrayList<String> listStars) {
		this.listStars = listStars;
	}
	
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	
	public void addDirector(String dir) {
		this.director += ", " + dir;
	}
	
	public void addGenre(String g) {
		this.listGenres.add(g);
	}
	
	@Override
	public String toString() {
		String temp = "";
		temp += "\nTitle: " + this.getTitle() +
			"\nYear: " + this.getYear() +
			"\nDirectors: " + this.getDirector() +
			"\nGenres: " + this.getListGenres().toString();
		return temp;
	}
}
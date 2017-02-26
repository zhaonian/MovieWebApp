/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Luan
 */
public class MovieDirStarCollection {

	private String dirName;
//	private ArrayList<String> currentStars = new ArrayList<>();
	private HashMap<String, ArrayList<String>> movieStarsMap = new HashMap<>();
//	private ArrayList<String> moviesTitle = new ArrayList<>();
//	private ArrayList<String> starsName = new ArrayList<>();

	public MovieDirStarCollection() {

	}

	public MovieDirStarCollection(String dirName) {
		this.dirName = dirName;
//		this.currentStars = new ArrayList<>();
//		this.moviesTitle = moviesTitle;
//		this.starsName = starsName;
	}

	public String getDirName() {
		return dirName;
	}

	public HashMap<String, ArrayList<String>> getMovieStarsMap() {
		return movieStarsMap;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public void setMovieStarsMap(HashMap<String, ArrayList<String>> movieStarsMap) {
		this.movieStarsMap = movieStarsMap;
	}

//	public void addMovieKey(String movieTitle) {
//		this.movieStarsMap.put(movieTitle, null);
//	} 
	public void addIntoMap(String movieTitle, String starName) {
		ArrayList<String> tempStarsList;
		if (!this.movieStarsMap.containsKey(movieTitle)) { // if not exist in map
//			this.currentStars.add(starName);
			tempStarsList = new ArrayList<String>();
		} else { // if exists in map
			tempStarsList = this.movieStarsMap.get(movieTitle);
		}
		tempStarsList.add(starName);
		this.movieStarsMap.put(movieTitle, tempStarsList);
	}

	@Override
	public String toString() {
		String temp = "";
		temp += "director: " + this.dirName + "\n";
		for (HashMap.Entry<String, ArrayList<String>> entry : this.movieStarsMap.entrySet()) {
			temp += "movie: " + entry.getKey() + "\n";
			for (String star : entry.getValue()) {
				temp += "star: " + star + "\n";
			}
		}
		return temp;
	}

}

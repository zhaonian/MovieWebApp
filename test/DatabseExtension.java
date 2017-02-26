/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Luan
 */
public class DatabseExtension {

	public DatabseExtension() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void hello() {

		backend.DBConnection dbConnection = new backend.DBConnection();

		MovieSAXParser movieSAXParser = new MovieSAXParser();
		movieSAXParser.parseDocument("mains243.xml");
		ArrayList<backend.Movie> movieList = movieSAXParser.getMovieList();

		backend.XMLInsertion XMLinsertion = new backend.XMLInsertion(dbConnection.get_connection());
		backend.StoredProcedure storedProc = new backend.StoredProcedure(dbConnection.get_connection());

		HashSet<String> genreSet = new HashSet<String>();

		for (backend.Movie movie : movieList) {	// insert movies;
//			System.out.println("---" + movie.toString());

			String title = movie.getTitle();
			int year = movie.getYear();
			String director = movie.getDirector();

			ArrayList<String> genreList = movie.getListGenres();
			for (String genre : genreList) {
				storedProc.insertMovie(title, year, director, null, null, "", "", genre);
			}
		}
//			XMLinsertion.insertSingleMovie(title, year, director);

//			for (String genre : genreList) {
//				genreSet.add(genre);
//			}
//		}
//		for (String genre : genreSet) {	// insert genres
//			XMLinsertion.insertGenre(genre);
//		}
//		for (backend.Movie movie : movieList) {	// insert genres_in_movies
//			String title = movie.getTitle();
//			ArrayList<String> genreList = movie.getListGenres();
//			for (String genre : genreList) {
//				XMLinsertion.insertGenresInMovies(genre, title);
//			}
//		}
		StarSAXParser starSAXParser = new StarSAXParser();
		starSAXParser.parseDocument("casts124.xml");
		ArrayList<backend.MovieDirStarCollection> movieDirStarCollectionList = starSAXParser.getMovieDirStarList();

		for (backend.MovieDirStarCollection m : movieDirStarCollectionList) {
//			System.out.println(m.toString());
			String director = m.getDirName();
			HashMap<String, ArrayList<String>> map = m.getMovieStarsMap();
			for (HashMap.Entry<String, ArrayList<String>> entry : map.entrySet()) {
				String title = entry.getKey();
				for (String star : entry.getValue()) {
					String firstName, lastName;
					String[] names = star.trim().split(" ");
					if (names.length == 0) {
						firstName = "";
						lastName = "";
					} else if (names.length == 1) {
						firstName = names[0];
						lastName = "";
					} else {
						firstName = names[0];
						lastName = names[1];
					}

					storedProc.insertMovie(title, 0, director, null, null, firstName, lastName, "");
				}

			}
		}
	}
}

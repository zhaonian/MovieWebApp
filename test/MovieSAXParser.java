
/**
 *
 * @author Luan
 */
import backend.Movie;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class MovieHandler extends DefaultHandler {

	private int state = 0;
	private final ArrayList<backend.Movie> movieList = new ArrayList<>();
	private backend.Movie currentMovie = new backend.Movie();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		OUTER:
		switch (state) {
			case 0:
			case 100:
				if (qName.equals("film")) {
					state = 1;
					currentMovie = new backend.Movie();
					break;
				} else {
					state = 0;
					break;
				}
			case 1:
				switch (qName) {
					case "fid":
						state = 2;
						break OUTER;
					case "t":
						state = 3;
						break OUTER;
					case "year":
						state = 4;
						break OUTER;
					case "dirs":
						state = 5;
						break OUTER;
					case "cats":
						state = 7;
						break OUTER;
					default:
						break;
				}
			case 5:
				if (qName.equals("dirn")) {
					state = 6;
					break;
				}
			case 7:
				if (qName.equals("cat")) {
					state = 8;
					break;
				}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (state) {
			case 1:
				if (qName.equals("film")) {
					state = 100;
					movieList.add(currentMovie);
					break;
				}
			case 2:
				if (qName.equals("fid")) {
					state = 1;
					break;
				}
			case 3:
				if (qName.equals("t")) {
					state = 1;
					break;
				}
			case 4:
				if (qName.equals("year")) {
					state = 1;
					break;
				}
			case 5:
				if (qName.equals("dirs")) {
					state = 1;
					break;
				}
			case 6:
				if (qName.equals("dirn")) {
					state = 5;
					break;
				}
			case 7:
				if (qName.equals("cats")) {
					state = 1;
					break;
				}
			case 8:
				if (qName.equals("cat")) {
					state = 7;
					break;
				}
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		// movie id
//		if (state == 2) {
//			String id = "";
//			for (int i = start; i < start + length; i++) {
//				id += ch[i];
//			}
////			currentMovie.setId(Integer.parseInt(id));
//		}
		// title
		if (state == 3) {
			String title = "";
			for (int i = start; i < start + length; i++) {
				title += ch[i];
			}
			currentMovie.setTitle(title);
		}
		// year
		if (state == 4) {
			String year = "";
			for (int i = start; i < start + length; i++) {
				year += ch[i];
			}
			int y = 0;
			try {
				y = Integer.parseInt(year);
			} catch (NumberFormatException ex) {
				year = "-1";
			}
			currentMovie.setYear(y);
		}
		// directors
		if (state == 6) {
			String director = "";
			for (int i = start; i < start + length; i++) {
				director += ch[i];
			}
			currentMovie.setDirector(director);
		}
		// genres
		if (state == 8) {
			String genre = "";
			for (int i = start; i < start + length; i++) {
				genre += ch[i];
			}
			currentMovie.addGenre(genre);
		}
	}

	public ArrayList<backend.Movie> getMovieList() {
		return this.movieList;
	}
}

public class MovieSAXParser {

	private MovieHandler dh;

	public void parseDocument(String fileName) {

		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		dh = new MovieHandler();

		try {
			//get a new instance of parser
			javax.xml.parsers.SAXParser sp = spf.newSAXParser();

			//parse the file and also register this class for call backs
			sp.parse(new File(fileName), dh);

		} catch (SAXException | ParserConfigurationException | IOException se) {
			Logger.logMsg(Logger.ERROR, se.toString());
		}
	}

	public ArrayList<Movie> getMovieList() {
		return dh.getMovieList();
	}
}

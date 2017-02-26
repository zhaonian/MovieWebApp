
/**
 *
 * @author Luan
 */
import backend.MovieDirStarCollection;
import backend.Star;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class StarHandler extends DefaultHandler {

	private String tempMovieTitle;
	private int state = 0;
	private final ArrayList<backend.MovieDirStarCollection> movieDirStarList = new ArrayList<>();
	private backend.MovieDirStarCollection movieDirStar = new backend.MovieDirStarCollection();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (state) {
			case 0:
			case 100:
				if (qName.equals("dirfilms")) {
					state = 1;
					movieDirStar = new backend.MovieDirStarCollection(); ///////////////////
					break;
				} else {
					state = 0;
					break;
				}
			case 1:
				if (qName.equals("is")) {
					state = 2;
					break;
				} else if (qName.equals("filmc")) {
					state = 3;
					break;
				}
			case 3:
				if (qName.equals("m")) {
					state = 4;
					break;
				}
			case 4:
				if (qName.equals("a")) {
					state = 6;
					break;
				} else if (qName.equals("t")) {
					state = 5;
					break;
				}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (state) {
			case 1:
				if (qName.equals("dirfilms")) {
					state = 100;
					movieDirStarList.add(movieDirStar); ////////////////////////
					break;
				}
			case 2:
				if (qName.equals("is")) {
					state = 1;
					break;
				}
			case 3:
				if (qName.equals("filmc")) {
					state = 1;
					break;
				}
			case 4:
				if (qName.equals("m")) {
					state = 3;
					break;
				}
			case 5:
				if (qName.equals("t")) {
					state = 4;
					break;
				}
			case 6:
				if (qName.equals("a")) {
					state = 4;
					break;
				}
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		// movie title
		if (state == 5) {
			String movieTitle = "";
			for (int i = start; i < start + length; i++) {
				movieTitle += ch[i];
			}
//			if (!movieDirStar.getMoviesTitle().contains(movieTitle)) {
//				movieDirStar.addMoviesTitle(movieTitle);
//			}
			tempMovieTitle = movieTitle;
		}

		// star name
		if (state == 6) {
			String starName = "";
			for (int i = start; i < start + length; i++) {
				starName += ch[i];
			}
//			movieDirStar.addStarName(starName);
			movieDirStar.addIntoMap(tempMovieTitle, starName);
		}

		// director name
		if (state == 2) {
			String dirName = "";
			for (int i = start; i < start + length; i++) {
				dirName += ch[i];
			}
			movieDirStar.setDirName(dirName);
		}
	}

	public ArrayList<MovieDirStarCollection> getMovieDirStarList() {
		return this.movieDirStarList;
	}
}

public class StarSAXParser {

	private StarHandler dh;

	public void parseDocument(String fileName) {

		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		dh = new StarHandler();

		try {
			//get a new instance of parser
			javax.xml.parsers.SAXParser sp = spf.newSAXParser();

			//parse the file and also register this class for call backs
			sp.parse(new File(fileName), dh);

		} catch (SAXException | ParserConfigurationException | IOException se) {
			Logger.logMsg(Logger.ERROR, se.toString());
		}
	}

	public ArrayList<MovieDirStarCollection> getMovieDirStarList() {
		return dh.getMovieDirStarList();
	}
}

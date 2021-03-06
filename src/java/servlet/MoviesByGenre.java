/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luan
 */
public class MoviesByGenre extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (!(boolean) request.getSession().getAttribute("loggedIn")) {
			request.getRequestDispatcher("401.jsp").forward(request, response);
		}
		try {

			backend.DBConnection dbConnection = new backend.DBConnection();
			backend.MoviesByWhat moviesByWhat = new backend.MoviesByWhat(dbConnection.get_connection());

			ResultSet result = moviesByWhat.getMoviesByGenre(request.getParameter("genre"));
			ArrayList<backend.Movie> arrayMovie = new ArrayList<>();
			
			ArrayList<String> listGenres = new ArrayList<>();
			while (result.next()) {
				backend.Movie movie = new backend.Movie();

				movie.setId(result.getInt("id"));
				movie.setTitle(result.getString("title"));
				movie.setYear(result.getInt("year"));
				movie.setDirector(result.getString("director"));
				movie.setBanner_url(result.getString("banner_url"));
//				listGenres.add(result.getString("genres.name"));
				arrayMovie.add(movie);
			}
//			movie.setListGenres(listGenres);
			request.getSession().setAttribute("arrayMovie", arrayMovie);
			request.getRequestDispatcher("/NumPerPage").forward(request, response);

		} catch (SQLException ex) {
			Logger.getLogger(MoviesByGenre.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}

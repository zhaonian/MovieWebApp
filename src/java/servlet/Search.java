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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luan
 */
public class Search extends HttpServlet {

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
		try {
			String titlePattern = request.getParameter("title");
			String year = request.getParameter("year");
			String director = request.getParameter("director");
			String starFirstName = request.getParameter("starFirstName");
			String starLastName = request.getParameter("starLastName");
			
//			System.out.println(titlePattern);
			
			backend.DBConnection dbConnection = new backend.DBConnection();
			backend.Search search = new backend.Search(dbConnection.get_connection());
			ResultSet result = search.getMovieByTitle(titlePattern, year, director, starFirstName, starLastName);

			ArrayList<backend.Movie> arrayMovie = new ArrayList<>();

			while (result.next()) {
				backend.Movie movie = new backend.Movie();

				movie.setId(result.getInt("id"));
				movie.setTitle(result.getString("title"));
				movie.setYear(result.getInt("year"));
				movie.setDirector(result.getString("director"));
				movie.setBanner_url(result.getString("banner_url"));

				arrayMovie.add(movie);
			}
//			String method = request.getParameter("method");
//			if (method.equals("forward"))
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			request.getSession().setAttribute("arrayMovie", arrayMovie);
			request.getRequestDispatcher("NumPerPage").forward(request, response);
			dbConnection.closeConnection();

		} catch (SQLException ex) {
			Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
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

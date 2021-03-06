/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luan
 */
//@WebServlet(name = "ShoppingCart", urlPatterns = {"/ShoppingCart"})
public class ShoppingCart extends HttpServlet {

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

		String movieIDstr = request.getParameter("movieAddedToCart");

		backend.DBConnection dbConnection = new backend.DBConnection();
//		backend.CartInsertion cartInsertion = new backend.CartInsertion(dbConnection.get_connection());

		int movieID = Integer.parseInt(movieIDstr);

		ArrayList<backend.Movie> shoppingCart = (ArrayList<backend.Movie>) request.getSession().getAttribute("shoppingCart");
		backend.SingleMovie singleMovie = new backend.SingleMovie(dbConnection.get_connection());
		backend.Movie movie = singleMovie.getSingleMovie(movieID);

//		if (!shoppingCart.contains(movie)) {
		boolean duplicatedMovieExist = false;
		for (backend.Movie m : shoppingCart) {
			if (m.getId() == movie.getId()) {
				duplicatedMovieExist = true;
			}
		}
		if (!duplicatedMovieExist) {
			request.getSession().setAttribute("" + movie.getId(), 1);
			shoppingCart.add(movie);

		} else {
			request.getSession().setAttribute("" + movie.getId(), (int) request.getSession().getAttribute("" + movie.getId()) + 1);
		}
		request.getSession().setAttribute("total", (int) request.getSession().getAttribute("total") + 1);
		request.getSession().setAttribute("shoppingCart", shoppingCart);

//		String userEmail = (String) request.getSession().getAttribute("user_id");
//		cartInsertion.insertMovieIntoCart(userEmail, movieID, 0);  // need to implement num_copy
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
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

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
public class MovieInsertion extends HttpServlet {

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
		if (!(boolean) request.getSession().getAttribute("employeeloggedIn")) {
			request.getRequestDispatcher("401.jsp").forward(request, response);
		}

		String title = request.getParameter("title");
		int year;
		try {
			year = Integer.parseInt(request.getParameter("year"));
		} catch (NumberFormatException ex) {
			year = 0;
		}
		String director = request.getParameter("director");
		String banner = request.getParameter("banner");
		String trailer = request.getParameter("trailer");
		String firstName = request.getParameter("starFirstName");
		String lastName = request.getParameter("starLastName");
		String genre = request.getParameter("genre");

		
		
		
		backend.DBConnection dbConnection = new backend.DBConnection();
		backend.StoredProcedure storedProc = new backend.StoredProcedure(dbConnection.get_connection());
		
		int result = -1;
		result = storedProc.insertMovie(title, year, director, banner, trailer, firstName, lastName, genre);
		request.setAttribute("insertionStatus", result);
		request.setAttribute("starInsertSucceed", 0);
		request.setAttribute("infoArray", new ArrayList<>());

		request.getRequestDispatcher("dbManager.jsp").forward(request, response);
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

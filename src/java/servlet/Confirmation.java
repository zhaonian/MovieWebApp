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
public class Confirmation extends HttpServlet {

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
		backend.DBConnection dbConnection = new backend.DBConnection();
		backend.Confirmation confirmation = new backend.Confirmation(dbConnection.get_connection());

		String firstname = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String cardNumber = request.getParameter("creditCard");
		String expDate = request.getParameter("expDate");

		boolean confirmed = confirmation.verifyCreditCard(firstname, lastName, cardNumber, expDate);
		if (confirmed) {
			backend.SalesInsertion salesInsertion = new backend.SalesInsertion(dbConnection.get_connection());
			ArrayList<backend.Movie> moviesInCart = (ArrayList<backend.Movie>) request.getSession().getAttribute("shoppingCart");
			for (backend.Movie movie : moviesInCart) {
				System.out.println("a------------" + movie.getTitle());
				salesInsertion.insertSales((int) request.getSession().getAttribute("user_id"), movie.getId());
			}
		}
		
		request.getSession().setAttribute("shoppingCart", new ArrayList<backend.Movie>());
		request.setAttribute("confirmed", confirmed);
		request.getRequestDispatcher("confirmation.jsp").forward(request, response);
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

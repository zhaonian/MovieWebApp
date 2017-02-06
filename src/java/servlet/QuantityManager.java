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
public class QuantityManager extends HttpServlet {

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

		int qty = 0;

		if (request.getParameter("submit").equals("update")) {
			ArrayList<backend.Movie> movies
				= (ArrayList<backend.Movie>) request.getSession().getAttribute("shoppingCart");
			for (backend.Movie movie : movies) {
				try {
					int temp = Integer.parseInt(request.getParameter("" + movie.getId()));
					if (temp >= 0) {
						qty = temp;
					}
				} catch (Exception e) {
					continue;
				}
				request.getSession().setAttribute("" + movie.getId(), qty);
				if ((boolean) request.getSession().getAttribute("updateRemoveClicked") || movies.size() > 1) {
					request.getSession().setAttribute("total", (int) request.getSession().getAttribute("total") + qty);
				} else {
					request.getSession().setAttribute("total", qty);
				}
			}

		} else {
			for (backend.Movie movie : (ArrayList<backend.Movie>) request.getSession().getAttribute("shoppingCart")) {
				int temp;
				try {
					temp = Integer.parseInt(request.getParameter("" + movie.getId()));
					if (temp >= 0) {
						qty = (int) request.getSession().getAttribute("total") - temp;
					}
				} catch (Exception e) {
					continue;
				}
				request.getSession().setAttribute("" + movie.getId(), qty);
				if ((boolean) request.getSession().getAttribute("updateRemoveClicked")) {

					request.getSession().setAttribute("total", (int) request.getSession().getAttribute("total") - temp);
				}
			}
		}
		request.getSession().setAttribute("updateRemoveClicked", true);
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

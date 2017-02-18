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
public class EmployeeDashboard extends HttpServlet {

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

		int starInsertSucceed = 0;
		backend.DBConnection dbConnection = new backend.DBConnection();
		request.setAttribute("infoArray", new ArrayList<>());

		if (request.getParameter("method").equals("StarInsertion")) {
			String starName = request.getParameter("starName");

			if (!starName.equals("")) {

				backend.Star starModel = new backend.Star();

				if (starModel.insertStar(dbConnection.get_connection(), starName)) {
					starInsertSucceed = 1;
				} else {
					starInsertSucceed = 2;
				}
			} else {
				starInsertSucceed = 2;
			}
		} else {
			backend.DBMetaData dbMetaData = new backend.DBMetaData(dbConnection.get_connection());
			ArrayList<String> infoArray = dbMetaData.getMetaData();
			request.setAttribute("infoArray", infoArray);
		}

		request.setAttribute("starInsertSucceed", starInsertSucceed);
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

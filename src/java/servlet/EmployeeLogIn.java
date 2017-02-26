/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class EmployeeLogIn extends HttpServlet {

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
			// Output stream to STDOUT
			PrintWriter out = response.getWriter();

			String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
			// Verify CAPTCHA.
			boolean valid = backend.VerifyUtils.verify(gRecaptchaResponse);
			if (!valid) {
				//errorString = "Captcha invalid!";
				out.println("<HTML>"
					+ "<HEAD><TITLE>"
					+ "MovieDB: Error"
					+ "</TITLE></HEAD>\n<BODY>"
					+ "<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
				return;
			}

			backend.DBConnection dbConnection = new backend.DBConnection();
			backend.UserVerification userVerification = new backend.UserVerification(dbConnection.get_connection());

			String email = request.getParameter("email");
			String passwd = request.getParameter("password");

			ResultSet result = userVerification.verifyEmployee(email, passwd);

			int starInsertSucceed = 0;
			request.setAttribute("starInsertSucceed", starInsertSucceed);
			request.setAttribute("infoArray", new ArrayList<>());

			if (result.next()) {
				boolean employeeloggedIn = true;
				request.getSession().setAttribute("employeeloggedIn", employeeloggedIn);
				request.setAttribute("insertionStatus", -1);
				request.getRequestDispatcher("dbManager.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("401.jsp").forward(request, response);
			}

		} catch (SQLException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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

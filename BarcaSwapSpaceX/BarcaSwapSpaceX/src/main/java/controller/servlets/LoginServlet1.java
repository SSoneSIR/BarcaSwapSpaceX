package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.dbController;
import model.LoginModel;
import utils.StringUtils;

/**
 * This Servlet class handles login requests for a student management system. It
 * retrieves username and password from the login form submission, validates
 * them against a database using a `DBController`, and redirects the user
 * accordingly based on the login result.
 *
 * @author [Yashaswan Gautam, yashaswanyasu@gmail.com]
 */
@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LoginServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final dbController dbController;

	public LoginServlet1() {
		this.dbController = new dbController();
	}

	/**
	 * Handles HTTP POST requests for login.
	 *
	 * @param request  The HttpServletRequest object containing login form data.
	 * @param response The HttpServletResponse object for sending responses.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Extract email and password from the request parameters
		String email = request.getParameter(StringUtils.EMAIL);
		String password = request.getParameter(StringUtils.PASSWORD);
		// Create a LoginModel object to hold user credentials
		LoginModel loginModel = new LoginModel(email, password);

		// Call DBController to validate login credentials
		int loginResult = dbController.getUserLoginInfo(loginModel);

		// Handle login results with appropriate messages and redirects
		if (loginResult == 1) {
			// Login successful
			HttpSession userSession = request.getSession();

			userSession.setAttribute(StringUtils.EMAIL, email);
			userSession.setMaxInactiveInterval(30* 60);

			Cookie userCookie = new Cookie(StringUtils.EMAIL, email);
			userCookie.setMaxAge(30 * 60);
			response.addCookie(userCookie);

			if ("admin".equals(dbController.getUserRole(email))) {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Welcome admin");
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_HOME);
			} else if ("user".equals(dbController.getUserRole(email))) {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Welcome user");
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_HOME);
			} else {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
				request.getRequestDispatcher(StringUtils.PAGE_URL_HOME).forward(request, response);
			}
		} else if (loginResult == 0) {
			// Incorrect password for an existing email
			request.setAttribute("inc_Password", "Incorrect Password");
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_PASSWORD_UNMATCHED);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
			System.out.print(loginResult);
		} else if (loginResult == -1) {
			// Non-existing email
			request.setAttribute("NoEmailFound", "Email doesnt exist. Try again with another email.");
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_EMAIL);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
			System.out.print(loginResult);
		} else {
			// Internal server error
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
			System.out.print(loginResult);
		}
	}
}

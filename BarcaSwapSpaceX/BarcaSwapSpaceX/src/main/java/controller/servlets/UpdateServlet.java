package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import utils.StringUtils;
/**
 * @author [Yashaswan Gautam, yashaswanyasu@gmail.com]
 */
/**
 * Servlet implementation class UpddateServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_UPDATE })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final dbController dbController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		this.dbController = new dbController();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String firstName = request.getParameter(StringUtils.FIRST_NAME);
			String lastName = request.getParameter(StringUtils.LAST_NAME);
			String userName = request.getParameter(StringUtils.USERNAME);
			String birthdayParam = request.getParameter(StringUtils.BIRTHDAY);
			LocalDate birthday = null;
			if (birthdayParam != null && !birthdayParam.isEmpty()) {
				birthday = LocalDate.parse(birthdayParam);
			} else {
				return;
			}
			String phoneNumber = request.getParameter(StringUtils.PHONE_NO);
			String country = request.getParameter(StringUtils.COUNTRY);
			String userEmail = (String) request.getSession().getAttribute(StringUtils.EMAIL);

			if (dbController.isPhoneExists(phoneNumber)) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Phone number already exists");
				request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);
				return;
			}

			if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Invalid phone number format");
				request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);
				return;
			}

			if (birthday.isAfter(LocalDate.now())) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Birthday cannot be in the future");
				request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);
				return;
			}
			if (!phoneNumber.matches("\\d+")) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Phone number must contain only digits");
				request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);
				return;
			}

			// Validate the updated data (similar to registration validation)

			// Update user profile in the database
			int updateResult = dbController.updateUserProfile(firstName, lastName, userName, phoneNumber, birthday,
					country, userEmail);
			if (updateResult == 1) {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Update Sucessfull");
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_PROFILE);
			} else {
				request.setAttribute("email_error", "Email already exists");
				request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);
			}

			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

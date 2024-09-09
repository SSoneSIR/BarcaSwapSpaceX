package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.RegisterModel;
import utils.StringUtils;
/**
 * @author [Yashaswan Gautam, yashaswanyasu@gmail.com]
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
/*
 * @MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2mb maxFileSize =
 * 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
 */

public class RegisterServlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final dbController dbController;

	public RegisterServlet1() {

		this.dbController = new dbController();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String userName = request.getParameter("userName");
			String phoneNumber = request.getParameter("phone");
			LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
			String country = request.getParameter("country");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm_password");
			String role = request.getParameter("user_type");

			System.out.println("First Name: " + firstName + ", Last Name: " + lastName + ", Username: " + userName
					+ ", Phone Number: " + phoneNumber + ", Birthday: " + birthday + ", Country: " + country
					+ ", Email: " + email + ", Password: " + password + ", Confirm Password: " + confirmPassword
					+ ", Role: " + role);

						/*
			 * String savePath = StringUtils.IMAGE_DIR_SAVE_PATH; String fileName =
			 * registerUser.getImageUrlFromPart();
			 */
			/*
			 * if (!fileName.isEmpty() && fileName != null) { imagePart.write(savePath +
			 * fileName); }
			 */
			if (firstName == null || lastName == null || userName == null || phoneNumber == null || birthday == null
					|| country == null || email == null || password == null || confirmPassword == null || role == null
					|| firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || phoneNumber.isEmpty()
					|| country.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
					|| role.isEmpty()) {
				throw new IllegalArgumentException("One or more required fields are empty");
			}
			if (!firstName.matches("[a-zA-Z]+")) {
				request.setAttribute("firstNameNodigit", "First name must contain only letters");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
				return;
			}

			if (!lastName.matches("[a-zA-Z]+")) {
				request.setAttribute("lastNameNodigit", "Last name must contain only letters");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
				return;
			}
			if (dbController.isPhoneExists(phoneNumber)) {
				request.setAttribute("phoneAlreadyExit", "Phone number already exists");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
				return;
			}

			if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
				request.setAttribute("phoneSyntaxError", "Invalid phone number format");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
				return;
			}

			if (birthday.isAfter(LocalDate.now())) {
				request.setAttribute("birthdayError", "Birthday cannot be in the future");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
				return;
			}

			if (!password.equals(confirmPassword)) {
				request.setAttribute("passwordsDifferent", "Passwords do not match");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
				return;
			}
			if (!phoneNumber.matches("\\d+")) {
				request.setAttribute("phoneOnlyDigits", "Phone number must contain only digits");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
				return;
			}
			RegisterModel registerUser = new RegisterModel(firstName, lastName, userName, phoneNumber, birthday,
					country, email, password, confirmPassword, role);

			int result = dbController.registerUser(registerUser);

			if (result == 1) {
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
				request.setAttribute("username", userName);
				RegisterModel user = dbController.getUserDetailsByEmail(email);

				// Set the retrieved user details as attributes in the request object
				request.setAttribute("registeredUser", user);

				// Forward the request to the profile page
				request.getRequestDispatcher(StringUtils.PAGE_URL_PROFILE).forward(request, response);
			} else if (result == -1) {
				request.setAttribute("email_error", "Email already exists");
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			} else {
				request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}

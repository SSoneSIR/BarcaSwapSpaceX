package controller.servlets;

import java.io.IOException;

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
@WebServlet(urlPatterns = { "/DeletePage" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updateID = req.getParameter("email");

		if (updateID != null && !updateID.isEmpty()) {
			doDelete(req, resp);
		}
		String deleteID = req.getParameter("deleteId");

		if (deleteID != null && !deleteID.isEmpty()) {
			deletePlayer(req, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doPut(req, resp);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("calling dodelete");
		// Retrieve the user's email from the request parameter
		String email = request.getParameter("email");

		// Instantiate your dbController
		dbController controller = new dbController();

		try {
			// Call a method in your dbController to delete the user's account
			int result = controller.deleteUserByEmail(email);

			if (result == 1) {
				// Redirect the user to a success page or to the login page
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
			} else {
				// Redirect the user to an error page if deletion failed
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
			}
		} catch (Exception e) {
			// Handle any exceptions that might occur
			e.printStackTrace();
		}
	}

	protected void deletePlayer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("calling deletePlayer");
		String deleteID = request.getParameter("deleteId");
		System.out.println(deleteID);

		// Instantiate your dbController
		dbController controller = new dbController();

		try {
			// Call a method in your dbController to delete the user's account
			int result = controller.deletePlayerBySN(deleteID);

			if (result == 1) {
				// Redirect the user to a success page or to the login page
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADMIN);
			}
		} catch (Exception e) {
			// Handle any exceptions that might occur
			e.printStackTrace();
		}
	}
}

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
/**
 * Servlet implementation class EditPlayer
 */
@WebServlet("/EditPlayerPage")
public class EditPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static dbController controller;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPlayer() {
		controller = new dbController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateid = request.getParameter(StringUtils.UPDATE_ID);
		String playerName = request.getParameter(StringUtils.NAME);
		String playerPosition = request.getParameter(StringUtils.POSITION);
		String playerNation = request.getParameter(StringUtils.NATION);
		String playerAge = request.getParameter(StringUtils.AGE);
		String playerHeight = request.getParameter(StringUtils.HEIGHT);
		String playerWeight = request.getParameter(StringUtils.WEIGHT);
		String playerPreFoot = request.getParameter(StringUtils.PRE_FOOT);

		int updateResult = controller.updatePlayer(playerName, playerPosition, playerNation, playerAge, playerHeight,
				playerWeight, playerPreFoot, updateid);
		if (!playerName.matches("[a-zA-Z]+")) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Player name must contain only letters");
			request.getRequestDispatcher(StringUtils.PAGE_EDIT_PLAYER).forward(request, response);
			return;
		}
		if (!playerAge.matches("\\d+")) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Age must contain only digits");
			request.getRequestDispatcher(StringUtils.PAGE_EDIT_PLAYER).forward(request, response);
			return;
		}
		if (!playerHeight.matches("\\d+")) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Height must contain only digits");
			request.getRequestDispatcher(StringUtils.PAGE_EDIT_PLAYER).forward(request, response);
			return;
		}
		if (!playerWeight.matches("\\d+")) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Weight must contain only digits");
			request.getRequestDispatcher(StringUtils.PAGE_EDIT_PLAYER).forward(request, response);
			return;
		}
		if (updateResult == 1) {
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, "Update Sucessfull");
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADMIN);
		} else {
			request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
		}
		System.out.println(updateid);
	}

}

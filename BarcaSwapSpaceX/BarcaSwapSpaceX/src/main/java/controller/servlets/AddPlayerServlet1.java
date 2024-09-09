package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.dbController;
import model.PlayersModel;
import utils.StringUtils;
/**
 * @author [Yashaswan Gautam, yashaswanyasu@gmail.com]
 */
/**
 * Servlet implementation class AddPlayerServlet1
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADD })
public class AddPlayerServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPlayerServlet1() {
		super();

	}

	/**
	 * getUserDetailsByEmail
	 * 
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
		try {
			String playerName = request.getParameter(StringUtils.NAME);
			String playerPosition = request.getParameter(StringUtils.POSITION);
			String playerNation = request.getParameter(StringUtils.NATION);
			String playerAge = request.getParameter(StringUtils.AGE);
			String playerHeight = request.getParameter(StringUtils.HEIGHT);
			String playerWeight = request.getParameter(StringUtils.WEIGHT);
			String playerPreFoot = request.getParameter(StringUtils.PRE_FOOT);
			String playerSQNumber = request.getParameter(StringUtils.SQ_NUMBER);

			dbController controller = new dbController();

			PlayersModel players = new PlayersModel(playerName, playerPosition, playerNation, playerAge, playerHeight,
					playerWeight, playerPreFoot, playerSQNumber);

			if (!playerName.matches("[a-zA-Z]+")) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Name must contain only letters");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
				return;
			}
			if (!playerAge.matches("\\d+")) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Age must contain digits only");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
				return;
			}
			if (!playerHeight.matches("\\d+")) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Height must contain digits only ");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
				return;
			}
			if (!playerWeight.matches("\\d+")) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, "Weight must contain digits only");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
				return;
			}

			if (controller.isSQNExists(playerSQNumber)) {
				request.setAttribute(StringUtils.MESSAGE_ERROR,
						"Squad Number is expected to be unique for all the players.");
				request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);
				return;
			}
			int result = controller.addPlayer(players);

			if (result == 1) {
				request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
				response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_ADMIN);
			} else if (result == -1) {
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
				request.getRequestDispatcher(StringUtils.PAGE_ADD_PLAYER).forward(request, response);
			} else {
				request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
				request.getRequestDispatcher(StringUtils.PAGE_ADD_PLAYER).forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(request.getContextPath() + StringUtils.PAGE_ADD_PLAYER).forward(request,
					response);
		}
	}

}
package utils;

import java.io.File;

public class StringUtils {

	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/transfers";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	// End: DB Connection

	// Start: Queries
	
	
			public static final String QUERY_DELETE_PLAYER="DELETE FROM players WHERE playerID = ?";
	public static final String QUERY_REGISTER_USER = "INSERT INTO users ("
			+ "firstName, lastName, userName, phone, birthday, country, Email, Password, role) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM users WHERE Email = ?";

	public static final String QUERY_GET_USER_ROLE = "SELECT role FROM users WHERE Email = ?";

	public static final String QUERY_FOR_UPDATE = "UPDATE users SET firstName = ?, lastName = ?, userName = ?, phone = ?, birthday = ?, country = ? WHERE Email = ?";

	public static final String QUERY_ADD_PLAYER = "INSERT INTO players("
		+ "Name, Position, Nation, Age, Height, Weight, PreferredFoot, SquadNumber)"
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String QUERY_GET_ALL_PLAYERS = "SELECT * FROM players";
	
	public static final String DELETE_USER = "DELETE FROM users WHERE email = ?";

	public static final String DELETE_PLAYER = "DELETE FROM players WHERE SquadNumber	 = ?";
	
	public static final String QUERY_UPDATE_PLAYER = "UPDATE players SET Name = ?, Position = ?, Nation = ?, Age = ?, Height = ?, Weight = ?, PreferredFoot=? WHERE SquadNumber = ?";


		// End: Queries
	public static final String PLAYER_ID = "PlayerID";
	public static final String NAME = "Name";
	public static final String POSITION = "Position";
	public static final String NATION = "Nation";
	public static final String AGE = "Age";
	public static final String HEIGHT = "Height";
	public static final String WEIGHT = "Weight";
	public static final String PRE_FOOT = "PreferredFoot";
	public static final String SQ_NUMBER = "SquadNumber";
	
		// Start: Parameter names
	public static final String USER = "user";
	public static final String USER_ID = "userID";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String USERNAME = "userName";
	public static final String PHONE_NO = "phone";
	public static final String BIRTHDAY = "birthday";
	public static final String COUNTRY = "country";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String CONFIRM_PASSWORD = "confirm_password";
	public static final String ROLE = "RoleID";
	public static final String ROLE_NAME = "RoleName";
	public static final String VERIFICATION = "Verification";
	// End: Parameter names

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";

	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_HOME = "/pages/Home.jsp";
	public static final String PAGE_URL_INDEX = "/index.jsp";
	public static final String PAGE_URL_ADMIN = "/pages/ManagePlayers.jsp";
	public static final String PAGE_URL_PROFILE = "/pages/userProfile.jsp";
	public static final String PAGE_ADD_PLAYER = "/pages/ManagePlayers.jsp";
	public static final String PAGE_EDIT_PLAYER = "/pages/editPlayer.jsp";


	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/LoginPage";
	public static final String SERVLET_URL_REGISTER = "/RegisterPage";
	public static final String SERVLET_URL_LOGOUT = "/LogoutPage";
	public static final String SERVLET_URL_UPDATE = "/UpdatePage";
	public static final String SERVLET_URL_ADD = "/AddPage";
	public static final String SERVLET_URL_DELETE = "/DeletePage";


	// End: Servlet Route
	//Uploading images
	public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C;" + File.separator+IMAGE_DIR;
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";

}
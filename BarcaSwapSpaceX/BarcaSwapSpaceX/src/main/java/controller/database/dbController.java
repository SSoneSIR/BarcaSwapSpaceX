package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.PlayersModel;
import model.LoginModel;
import model.RegisterModel;
import utils.StringUtils;

public class dbController {
/**
 * @author [Yashaswan Gautam, yashaswanyasu@gmail.com]
 */
	/**
	 * Establishes a connection to the database using pre-defined credentials and
	 * driver information.
	 *
	 * @return A `Connection` object representing the established connection to the
	 *         database.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtils.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	public String getUserRole(String email) {
		try {
			// Prepare a statement using the predefined query for retrieving user role
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_USER_ROLE);

			// Set the email in the prepared statement
			stmt.setString(1, email);

			// Execute the query and store the result set
			ResultSet resultSet = stmt.executeQuery();

			// Check if there's a record returned from the query
			if (resultSet.next()) {
				// Get the role from the database
				return resultSet.getString("role");
			} else {
				// User not found in the database, return null or any default role
				return null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return null or any default role to indicate an error
			return null;
		}
	}

	public RegisterModel getUserDetailsByEmail(String email) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			// Get a connection to the database
			connection = getConnection();

			// Prepare SQL statement to retrieve user details by email

			stmt = connection.prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
			stmt.setString(1, email);

			// Execute the query
			resultSet = stmt.executeQuery();

			// Check if a user with the given email exists
			if (resultSet.next()) {
				// Extract user details from the result set
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String userName = resultSet.getString("userName");
				String phoneNumber = resultSet.getString("phone");
				LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
				String country = resultSet.getString("country");
				String retrievedEmail = resultSet.getString("email");
				String role = resultSet.getString("role");

				// Create a RegisterModel object with the retrieved details
				RegisterModel user = new RegisterModel(firstName, lastName, userName, phoneNumber, birthday, country,
						retrievedEmail, role);
				return user;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Handle exceptions
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Return null if no user found with the given email
		return null;
	}

	/**
	 * This method attempts to register a new user in the database.
	 *
	 * @param user A `RegisterModel` object containing the user's information.
	 * @return An integer value indicating the registration status: - 1:
	 *         Registration successful - 0: Registration failed (no rows affected) -
	 *         -1: Internal error (e.g., ClassNotFound or SQLException)
	 */
	public int registerUser(RegisterModel user) {

		try {
			// Prepare a statement using the predefined query for user registration
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);

			// Set the user information in the prepared statement
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getUserName());
			stmt.setString(4, user.getPhone());
			stmt.setDate(5, Date.valueOf(user.getBirthday()));
			stmt.setString(6, user.getCountry());
			stmt.setString(7, user.getEmail());
			stmt.setString(8, user.getPassword());
			stmt.setString(9, user.getRole());
			/* stmt.setString(10, user.getRole()); */
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}

	/**
	 * This method attempts to validate a user login by checking the email and
	 * password against the database.
	 *
	 * @param loginModel The `LoginModel` object containing the user's login
	 *                   credentials.
	 * @return An integer value indicating the login status: - 1: Login successful -
	 *         0: Email or password mismatch - -1: Email not found in the database -
	 *         -2: Internal error (e.g., SQL or ClassNotFound exceptions)
	 */
	public int getUserLoginInfo(LoginModel loginModel) {

		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try {
			// Prepare a statement using the predefined query for login check
			PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

			// Set the EMAIL in the first parameter of the prepared statement
			st.setString(1, loginModel.getEmail());

			// Execute the query and store the result set
			ResultSet result = st.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the EMAIL from the database
				String userDb = result.getString(StringUtils.EMAIL);

				// Get the password from the database
				String passwordDb = result.getString(StringUtils.PASSWORD);

				// Check if the email and password match the credentials from the database
				if (userDb.equals(loginModel.getEmail()) && passwordDb.equals(loginModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Email or password mismatch, return 0
					return 0;
				}
			} else {
				// Email not found in the database, return -1
				return -1;
			}

			// Catch SQLException and ClassNotFoundException if they occur
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	/**
	 * Checks if the provided phone number already exists in the database.
	 *
	 * @param phoneNumber The phone number to check for existence.
	 * @return true if the phone number exists in the database, false otherwise.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public boolean isPhoneExists(String phoneNumber) throws SQLException, ClassNotFoundException {
		// Establish connection to the database
		try (Connection connection = getConnection()) {
			// Prepare SQL statement
			String sql = "SELECT COUNT(*) FROM users WHERE phone = ?";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, phoneNumber);

				try (ResultSet resultSet = stmt.executeQuery()) {
					// Check if any record exists with the provided phone number
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		}
		return false; // Default to false if an exception occurs
	}

	public boolean isSQNExists(String squadNumber) throws SQLException, ClassNotFoundException {
		try (Connection connection = getConnection()) {
			String sql = "SELECT COUNT(*) FROM players WHERE SquadNumber = ?";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, squadNumber);

				try (ResultSet resultSet = stmt.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			throw ex;
		}
		return false;
	}

	public int updateUserProfile(String firstName, String lastName, String userName, String phone, LocalDate birthday,
			String country, String userEmail) {
		try {
			// Get a connection to the database
			Connection connection = getConnection();

			// Prepare SQL statement to update user profile

			PreparedStatement stmt = connection.prepareStatement(StringUtils.QUERY_FOR_UPDATE);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, userName);
			stmt.setString(4, phone);
			stmt.setDate(5, Date.valueOf(birthday));
			stmt.setString(6, country);
			stmt.setString(7, userEmail);

			// Execute the update statement
			int updateResult = stmt.executeUpdate();

			if (updateResult > 0) {
				// If rows were affected, return 1 to indicate success
				return 1;
			} else {
				// If no rows were affected, return 0 to indicate failure
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Handle exceptions
			ex.printStackTrace();
			return -1;
		}
	}

	public int updatePlayer(String Name, String Position, String Nation, String Age, String Height, String Weight,
			String preferredFoot, String SquadNumber) {
		try {
			// Get a connection to the database
			Connection connection = getConnection();

			// Prepare SQL statement to update user profile

			PreparedStatement stmt = connection.prepareStatement(StringUtils.QUERY_UPDATE_PLAYER);
			stmt.setString(1, Name);
			stmt.setString(2, Position);
			stmt.setString(3, Nation);
			stmt.setString(4, Age);
			stmt.setString(5, Height);
			stmt.setString(6, Weight);
			stmt.setString(7, preferredFoot);
			stmt.setString(8, SquadNumber);

			// Execute the update statement
			int updateResult = stmt.executeUpdate();

			if (updateResult > 0) {
				// If rows were affected, return 1 to indicate success
				return 1;
			} else {
				// If no rows were affected, return 0 to indicate failure
				return 0;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Handle exceptions
			ex.printStackTrace();
			return -1;
		}
	}

	public int addPlayer(PlayersModel team) {
		try {
			// Prepare a statement using the predefined query for user registration
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_PLAYER);
			// Set the user information in the prepared statement
			stmt.setString(1, team.getName());
			stmt.setString(2, team.getPosition());
			stmt.setString(3, team.getNation());
			stmt.setString(4, team.getAge());
			stmt.setString(5, team.getHeight());
			stmt.setString(6, team.getWeight());
			stmt.setString(7, team.getPreferredFoot());
			stmt.setString(8, team.getSquadNumber());

			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) { // Print the stack trace for debugging purposes
			ex.printStackTrace();
		}
		return -1; // Internal error

	}

	public ArrayList<PlayersModel> getPlayers() {
		try {
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ALL_PLAYERS);
			ResultSet result = stmt.executeQuery();

			ArrayList<PlayersModel> playersAL = new ArrayList<PlayersModel>();

			while (result.next()) {
				PlayersModel players = new PlayersModel();
				players.setName(result.getString("Name"));
				players.setPosition(result.getString("Position"));
				players.setNation(result.getString("Nation"));
				players.setAge(result.getString("Age"));
				players.setHeight(result.getString("Height"));
				players.setWeight(result.getString("Weight"));
				players.setPreferredFoot(result.getString("PreferredFoot"));
				players.setSquadNumber(result.getString("SquadNumber"));

				playersAL.add(players);
			}
			return playersAL;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int deleteUserByEmail(String email) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0; // 0 indicates failure

		try {
			conn = getConnection(); // Implement getConnection() method to obtain a database connection

			stmt = conn.prepareStatement(StringUtils.DELETE_USER);
			stmt.setString(1, email);
			result = stmt.executeUpdate(); // Execute the DELETE statement
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle SQL exception
		}

		return result; // Return the result of the deletion process
	}

	public int deletePlayerBySN(String SN) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0; // 0 indicates failure

		try {
			conn = getConnection(); // Implement getConnection() method to obtain a database connection

			stmt = conn.prepareStatement(StringUtils.DELETE_PLAYER);
			stmt.setString(1, SN);
			result = stmt.executeUpdate(); // Execute the DELETE statement
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle SQL exception
		}

		return result; // Return the result of the deletion process
	}
}

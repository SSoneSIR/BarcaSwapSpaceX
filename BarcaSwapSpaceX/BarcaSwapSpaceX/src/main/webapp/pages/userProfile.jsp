	<%@page import="model.PlayersModel"%>
<%@page import="utils.StringUtils"%>
<%@page import="model.RegisterModel"%>
<%@page import="controller.database.dbController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.text.*"%>

<%
String contextPath = request.getContextPath();
String userName = (String) session.getAttribute("email"); // Get the user's email from session
dbController controller = new dbController();
ArrayList<PlayersModel> playersList = controller.getPlayers();
RegisterModel user = controller.getUserDetailsByEmail(userName);
String displayUsername = "";

if (userName != null) {
	String[] parts = userName.split("@");
	if (parts.length > 0) {
		// Get the username part
		displayUsername = parts[0];
	}
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>

</head>



<style>
body {
	font-family: Arial, sans-serif;
	color: #fff;
	margin: 0;
	padding: 0;
	height: 100vh;
}

#navbar1 {
	height: 50px;
	background-color: #050414;
	font-size: 10px;
	font-size: larger;
	text-decoration: none;
	display: flex;
	text-align: right;
	align-items: center;
	justify-content: flex-end;
}

header {
	background-color: #050414;
	padding: 0;
}

#navbar1 a {
	margin-right: 15px;
	text-decoration: none;
	color: #fff;
	font-size: 16px;
	display: flex;
	text-align: right;
	justify-content: flex-end;
}

.container {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
}

.profile-header h1 {
	color: black;
	text-align: center;
	margin-bottom: 20px;
}

.profile-content {
	display: flex;
	justify-content: space-between;
}

.profile-details {
	flex: 1;
	padding: 0 20px;
}

.profile-details input {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: none;
	border-radius: 5px;
	background-color: #27293d;
	color: #fff;
	font-size: 16px;
}

.profile-details button {
	width: 100%;
	padding: 10px;
	background-color: #d32f2f;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.profile-photo {
	flex: 1;
	padding: 0 20px;
	text-align: center;
}

.profile-photo img {
	max-width: 100%;
	border-radius: 50%;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

footer {
	background-color: #333;
	color: #fff;
	text-align: center;
	padding: 10px 0;
}

#navbar2 {
	background-color: #0d0c1d;
	align-items: center;
	display: flex;
	justify-content: flex-start;
	position: relative;
}

a {
	margin-right: 15px;
	text-decoration: none;
	color: white;
}

main {
	min-height: 120vh;
}

#bg {
	background-size: cover;
	background-position: center;
	min-height: 120vh;
	position: relative; /* Required for absolute positioning */
}

.profile-details {
	flex: 1;
	padding: 0 20px;
	display: flex;
	flex-direction: column;
}

/* Adjust input styles */
.profile-details input {
	width: calc(100% - 20px);
	padding: 10px;
	margin-bottom: 15px;
	border: none;
	border-radius: 5px;
	background-color: #27293d;
	color: #fff;
	font-size: 16px;
}

/* Adjust select styles */
.profile-details select {
	width: calc(100% - 20px);
	padding: 10px;
	margin-bottom: 20px;
	border: none;
	border-radius: 4px;
	background-color: #27293d;
	color: #fff;
	font-size: larger;
}

/* Update styles for profile photo */
.profile-photo {
	flex: 1;
	padding: 0 20px;
	text-align: center;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: flex-start;
}

/* Adjust file input styles */
#profile-photo-input {
	display: none; /* Hide the file input */
}

/* Adjust button styles */
.profile-photo button {
	width: 100%;
	padding: 10px;
	background-color: #d32f2f;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
	margin-top: 20px;
}

/* Adjust photo styles */
.profile-photo img {
	max-width: 100px;
	max-height: 200px;
	border-radius: 25%;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
	margin-bottom: 20px;
	margin-left: 100px;
	margin-bottom: 0px;
	height: 150px;
	position: absolute;
	bottom: auto;
	top: 90px;
	right: 650px;
	transition: transform 0.3s ease;
}

/* Add hover effect for profile photo */
.profile-photo img:hover {
	cursor: pointer;
}
</style>
<!-- <script>
	document.addEventListener("DOMContentLoaded", function() {
		// Get the profile photo element
		var profilePhoto = document.querySelector('.profile-photo img');

		// Get the file input element
		var fileInput = document.getElementById('profile-photo-input');

		// When the profile photo is clicked, trigger the file input
		profilePhoto.addEventListener('click', function() {
			fileInput.click();
		});
	});
</script> -->
<body>
	<header>
		<div id="navbar1">
			<%
			if (userName != null) {
			%>
			<a href="<%=contextPath%>/pages/userProfile.jsp"><span
				style="COLOR: white;"> Hi, <span style="COLOR: yellow;"><%=displayUsername%>
						!</span>
			</span></a>
			<%
			}
			%>
			<a href="<%=contextPath%>/pages/Home.jsp">Home</a> <a
				href="<%=contextPath%>/index.jsp"
				onclick="confirmLogout('<%=contextPath%>/index.jsp')">Logout</a>

		</div>
		<div id="navbar2">
			<a href="<%=contextPath%>/pages/Home.jsp">
			<img src="<%=contextPath%>/resources/logo.png" alt="Logo" width="100px"
				height="100px">
				</a> 
			<a id="News" href="News.html">Reports</a> 
			<a id="Tranfers" href="Tranfer.html">Transfers</a>
		</div>

	</header>

	<main>
		<div id="bg"
			style="background-image: url('<%=contextPath%>/resources/bg-image.jpg');">
			<div class="container">
				<div class="profile-content">
					<form action="<%=contextPath%>/UpdatePage" method="post">
						<div class="profile-details">
							<input type="text" name="userName" placeholder="Username"
								value="<%=user.getUserName()%>"> <input type="text"
								name="firstName" placeholder="First Name"
								value="<%=user.getFirstName()%>"> <input type="text"
								name="lastName" placeholder="Last Name"
								value="<%=user.getLastName()%>"> <input type="date"
								name="birthday" placeholder="Birthday"
								value="<%=user.getBirthday()%>"> <input type="text"
								name="phone" placeholder="Phone Number"
								value="<%=user.getPhone()%>"> <select name="country">
								<option value="<%=user.getCountry()%>"><%=user.getCountry()%></option>
								<option value="United States">United States
								<option value="China">China</option>
								<option value="India">India</option>
								<option value="Brazil">Brazil</option>
								<option value="Russia">Russia</option>
								<option value="Japan">Japan</option>
								<option value="Germany">Germany</option>
								<option value="United Kingdom">United Kingdom</option>
								<option value="France">France</option>
								<option value="Italy">Italy</option>
								<option value="Canada">Canada</option>
								<option value="South Korea">South Korea</option>
								<option value="Spain">Spain</option>
								<option value="Mexico">Mexico</option>
								<option value="Indonesia">Indonesia</option>
								<option value="Australia">Australia</option>
								<option value="Netherlands">Netherlands</option>
								<option value="Saudi Arabia">Saudi Arabia</option>
								<option value="Turkey">Turkey</option>
								<option value="Switzerland">Switzerland</option>
								<option value="Taiwan">Taiwan</option>
								<option value="Poland">Poland</option>
								<option value="Sweden">Sweden</option>
								<option value="Belgium">Belgium</option>
								<option value="Thailand">Thailand</option>
								<option value="Iran">Iran</option>
								<option value="Austria">Austria</option>
								<option value="Norway">Norway</option>
								<option value="United Arab Emirates">United Arab
									Emirates</option>
								<option value="Israel">Israel</option>


							</select>


							<%
							String err = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
							if (err != null) {
							%>
							<p style="color: red;">
								<%
								out.println(err);
								%>
							</p>
							<%
							}
							%>

							<button type="submit">Update Profile</button>
						</div>

					</form>
					<form id="deleteForm-<%=user.getEmail()%>" method="post"
						action="<%=contextPath + "/DeletePage"%>">
						<input type="hidden" name="email" value="<%=user.getEmail()%>" />
						<button type="submit"
							onclick="confirmDelete('<%=user.getEmail()%>')">Delete
							Account</button>
					</form>
				</div>
				<div class="profile-photo">

					<label for="profile-photo-input"> <img
						src="<%=contextPath%>/resources/user.JPG" alt="User Photo">
					</label> <input type="file" id="profile-photo-input" name="image"
						accept="image/*">

				</div>
			</div>
		</div>
	</main>



	<footer>
		<p>&copy; 2024 FC Barcelona. All rights reserved.</p>
	</footer>
</body>
<script type="text/javascript">
function confirmDelete(email) {
    console.log("Delete button clicked"); 
    if (confirm("Are you sure you want to delete your account: " + email + "?")) {
        document.getElementById("deleteForm-" + email).submit();
    }}
	function confirmLogout(logoutURL) {
		if (confirm("Are you sure you want to logout?")) {
			window.location.href = logout
			URL;
		}
		

	}

    </script>
</html>
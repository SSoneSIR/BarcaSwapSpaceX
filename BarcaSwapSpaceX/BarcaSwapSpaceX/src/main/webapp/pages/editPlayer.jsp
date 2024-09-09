<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="utils.StringUtils"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Player</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #100c1d;
	color: #fff;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	text-align: center;
	max-width: 500px;
	width: 100%;
	padding: 25px;
	border-radius: 8px;
	background-color: #1a1a2e;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.logo img {
	width: 100px;
}

h2 {
	margin-bottom: 20px;
	font-size: 1.5rem;
}

.divider {
	border-bottom: 1px solid #8c9eff;
	margin-bottom: 20px;
}

.input-group {
	display: flex;
	margin-bottom: 20px;
	flex-wrap: wrap;
}

.column {
	flex: 1;
	margin-right: 25px;
}

.column:last-child {
	margin-right: 0;
}
.divider {
    border-bottom: 1px solid #8c9eff;
    margin-bottom: 20px;
}


input[type="text"], input[type="tel"], input[type="date"], input[type="email"],
	input[type="password"], select {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: none;
	border-radius: 4px;
	background-color: #27293d;
	color: #fff;
	font-size: larger;
}

input[type="text"]:focus, input[type="tel"]:focus, input[type="date"]:focus,
	input[type="email"]:focus, input[type="password"]:focus {
	outline: none;
	box-shadow: 0 0 5px #8c9eff; /* Add a shadow when focused */
}

.login-btn {
	background-color: #d32f2f;
	color: white;
	border: none;
	padding: 10px 20px;
	text-transform: uppercase;
	font-weight: bold;
	cursor: pointer;
	width: 100%;
	border-radius: 4px;
}

.logo {
	margin-top: Opx;
}

a {
	text-decoration: none;
	color: white;
}

.signup-link {
	color: #8c9eff;
	text-decoration: none;
	transition: color 0.3s; /* Smooth transition for color change */
}

.signup-link:hover {
	color: #fff; /* Change color on hover */
}

.login-link {
	color: #8c9eff;
	text-decoration: none;
	transition: color 0.3s;
}

.login-link:hover {
	color: #fff; /* Change color on hover */
}

#file-label {
	display: inline-block;
	padding: 10px 20px;
	background-color: #d32f2f;
	color: white;
	cursor: pointer;
}

#image {
	display: none;
}

@media screen and (max-width: 414px) { /* iPhone 14 Pro Max width */
	/* Adjust width of select elements */
	select {
		width: 100%; /* Set width to full width for smaller screens */
		max-width: none; /* Remove maximum width to allow full expansion */
	}

	/* Adjust column width for better spacing */
	.column {
		flex: 1 1 100%;
		/* Allow column to grow to full width on smaller screens */
		margin-right: 0; /* Remove margin to avoid extra spacing */
	}
}
</style>
</head>

<body>
	<div class="container">
		<div class="logo">
			<a href="<%=contextPath%>/pages/Home.jsp"><img
				src="<%=contextPath%>/resources/logo.png" alt="Logo"></a>
		</div>
		<h2>Edit Player</h2>
		<div class="divider"></div>
		<form action="<%=contextPath%>/EditPlayerPage" method="post">
		<input type="hidden" name="${StringUtils.UPDATE_ID}" value=<%=request.getParameter("squadNo")%>>
			<!-- Replace with appropriate form fields for editing player details -->
			<div class="input-group">
				<div class="column">
					<input type="text" name="Name" placeholder="First Name"
						required>
				</div>
				<div class="column">
					<select name="Nation" required>
						<option value="" disabled selected>Select a country</option>
						<option value="United States">United States</option>
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
						<option value="United Arab Emirates">United Arab Emirates</option>
						<option value="Israel">Israel</option>
					</select>
				</div></div>
				<div class="input-group">
					<div class="column">
						<select name="Position" required>
							<option value="" disabled selected>Select a position</option>
							<option value="United States">Goal-keeper</option>
							<option value="China">Right-Back</option>
							<option value="India">Left-Back</option>
							<option value="India">Center-Back</option>
							<option value="India">Defenive-Midfielder</option>
							<option value="India">Central-Midfielder</option>
							<option value="India">Attacking-Midfielder</option>
							<option value="India">Left-Winger</option>
							<option value="India">Right-Winger</option>
							<option value="India">Center-Forward</option>
						</select>
					</div>
					<div class="column">
						<input type="text" name="Age" placeholder="Age" required>
					</div>
					<div class="input-group">
						<div class="column">
							<input type="text" name="Height" placeholder="Height" required>
						</div>
						<div class="column">
							<input type="text" name="Weight" placeholder="Weight" required>
						</div>
						<div class="input-group">
							<div class="column">
								<select name="PreferredFoot" required>
									<option value="" disabled selected>Preferred foot</option>
									<option value="United States">Left-footed</option>
									<option value="China">Right-footed</option>
								</select>
							</div>
							
						</div>
					</div>
				</div>
			<%
			String errEdit= (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
			if (errEdit != null) {
			%>
			<p style="color: red;"><%=errEdit%></p>
			<%
			}
			%>
			<button class="login-btn" onclick="window.history.back()">Back</button><div class="divider"></div>
			<button class="login-btn">Update</button>
		</form>
	</div>
</body>
</html>

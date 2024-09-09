<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="utils.StringUtils"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
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
	padding: 25px; /* Decreased padding */
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
<script type="text/javascript">
	function validateForm() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;

		if (password !== confirmPassword) {
			alert("Password and Confirm Password do not match");
			return false;
		}
		return true;
	}
	
    function displayFileName() {
        const input = document.getElementById('image');
        const label = document.getElementById('file-label');
        const fileName = input.files[0].name;
        label.textContent = fileName;
    }
</script>
<body>
	<div class="container">
		<div class="logo">
			<a href="<%=contextPath%>/index.jsp"><img
				src="<%=contextPath%>/resources/logo.png" alt="Logo"></a>
		</div>
		<h2>Join Us</h2>
		<div class="divider"></div>
		<form action="<%=contextPath%>/RegisterPage"
			onsubmit="return validateForm()" method="post" >


			<div class="input-group">
				<div class="column">
					<input type="text" name="firstName" placeholder="First Name"
						required>
				</div>
				<div class="column">
					<input type="text" name="lastName" placeholder="Last Name" required>
				</div>
			</div>
			<div class="input-group">
				<div class="column">
					<input type="text" name="userName" placeholder="Username" required>
				</div>
				<div class="column">
					<input type="tel" name="phone" placeholder="Phone Number" required>
				</div>
			</div>
			<div class="input-group">
				<div class="column">
					<input type="date" name="birthday" placeholder="Birthday" required>
				</div>
				<div class="column">
					<select name="country" required>
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

				</div>
			</div>
			<div class="input-group">
				<div class="column">
					<select name="user_type" required>
						<option value="" disabled selected>Select Role</option>
						<option value="user">User</option>
						<option value="admin">Admin</option>
					</select>
				</div>
			</div>
			<div class="input-group">
				<div class="column">
					<input type="email" name="email" placeholder="Email" required>
				</div>
				<div class="column">
					<input type="password" name="password" placeholder="Password"
						required>
				</div>
			</div>
			<div class="column">
				<input type="password" name="confirm_password"
					placeholder="Confirm Password" required>
				<%
				String passwordMismatchError = (String) request.getAttribute("passwordsDifferent");
				if (passwordMismatchError != null) {
				%>
				<p style="color: red;"><%=passwordMismatchError%></p>
				<%
				}
				%>
				<div class="row">
					<div class="col">
						<label for="image" id="file-label">Choose your profile picture</label>
						 <input type="file" id="image" name="image"
							onchange="displayFileName()">
					</div>
				</div>
			</div>
			<%
			String lastNameError = (String) request.getAttribute("lastNameNodigit");
			if (lastNameError != null) {
			%>
			<p style="color: red;"><%=lastNameError%></p>
			<%
			}
			%>

			<%
			String firstNameError = (String) request.getAttribute("firstNameNodigit");
			if (firstNameError != null) {
			%>
			<p style="color: red;"><%=firstNameError%></p>
			<%
			}
			%>

			<%
			String phoneInvalid = (String) request.getAttribute("phoneOnlyDigits");
			if (phoneInvalid != null) {
			%>
			<p style="color: red;"><%=phoneInvalid%></p>
			<%
			}
			%>

			<%
			String phoneError = (String) request.getAttribute("phoneAlreadyExist");
			if (phoneError != null) {
			%>
			<p style="color: red;"><%=phoneError%></p>
			<%
			}
			%>

			<%
			String birthdayError = (String) request.getAttribute("birthdayError");
			if (birthdayError != null) {
			%>
			<p style="color: red;"><%=birthdayError%></p>
			<%
			}
			%>

			<%
			String emailError = (String) request.getAttribute("email_error");
			if (emailError != null) {
			%>
			<p style="color: red;"><%=emailError%></p>
			<%
			}
			%>

			<%
			String phoneExits = (String) request.getAttribute("phoneSyntaxError");
			if (phoneExits != null) {
			%>
			<p style="color: red;"><%=phoneExits%></p>
			<%
			}
			%>

			<p class="login-question">
				Already have an account? <a href="<%=contextPath%>/pages/login.jsp"
					class="login-link"> Log In</a>
			</p>
			<button class="login-btn">Sign Up</button>
			<%
			String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
			String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

			if (errMsg != null) {
				// print
			%>
			<h2 class="success-msg">
				<%
				out.println(errMsg);
				%>
			</h2>
			<%
			}

			if (successMsg != null) {
			// print
			out.println(successMsg);
			}
			%>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
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
	width: 90%;
	padding: 40px 30px;
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

input[type="email"], input[type="password"] {
	width: calc(100% - 20px);
	padding: 15px;
	margin-bottom: 20px;
	border: none;
	border-radius: 4px;
	background-color: #27293d;
	color: #fff;
	font-size: larger;
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

.login-question {
	margin-bottom: 20px;
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

@media screen and (max-width: 500px) {
	.container {
		width: 100%;
	}
	input[type="email"], input[type="password"] {
		width: calc(100% - 20px);
	}
}
</style>
</head>
<body>
	<div class="container">
		<div class="logo">
			<a href="<%=contextPath%>/index.jsp"><img
				src="<%=contextPath%>/resources/logo.png" alt="Logo"></a>
		</div>
		<h2>Log In</h2>
		<div class="divider"></div>
		<form action="<%=contextPath%>/LoginPage" method="post">
			<div class="input-group">
				<input type="email" name="email" placeholder="Your email" required>
			</div>
			<div class="input-group">
				<input type="password" name="password" placeholder="Your password"
					required>
			</div>
			<%
			String WrgPass = (String) request.getAttribute("inc_Password");
			if (WrgPass != null) {
			%>
			<p style="color: red;"><%=WrgPass%></p>
			<%
			}
			%>
			<%
			String NoEmail = (String) request.getAttribute("NoEmailFound");
			if (NoEmail != null) {
			%>
			<p style="color: red;"><%=NoEmail%></p>
			<%
			}
			%>	
			<p class="login-question">
				Not registered yet? <a href="<%=contextPath%>/pages/register.jsp"
					class="signup-link"> Sign Up</a>
			</p>
			

			<button class="login-btn">Login</button>
		</form>
	</div>
</body>
</html>

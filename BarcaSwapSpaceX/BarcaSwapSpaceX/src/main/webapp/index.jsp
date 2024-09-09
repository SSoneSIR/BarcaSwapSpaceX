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
<title>FC Barcelona</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/index.css">
</head>
<body>
	<header>
		<div id="navbar1">
			<form action="LoginServlet" method="post">
				<a href="pages/login.jsp">Login</a>
			</form>
			<a href="<%=contextPath%>/pages/viewPlan.jsp"><button id="ViewPlan">View Plan</button></a>
		</div>
		<div id="navbar2">
			<a id="BarcaLogo" href="<%=contextPath%>/index.jsp"><img
				src="./resources/logo.png" width="100px" height="100px"></a> <

		</div>
	</header>
	<main>
		<section class="hero">
			<h1>Welcome to FC Barcelona</h1>
			<p>Experience the passion of football with us.</p>
		</section>
		<section class="news">
			
		</section>
	</main>
	<footer>
		<p>&copy; 2024 FC Barcelona. All rights reserved.</p>
	</footer>
</body>
</html>

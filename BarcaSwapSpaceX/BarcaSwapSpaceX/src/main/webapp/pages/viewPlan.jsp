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
<title>View Plan</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/viewplan.css">
</head>
<body>
    <header>
        <div id="navbar1">
            <a href="<%=contextPath%>/index.jsp">Home</a>
        </div>
        <div id="navbar2">
            <a id="BarcaLogo" href="<%= (session.getAttribute("email") != null) ? contextPath + "/pages/Home.jsp" : contextPath + "/index.jsp" %>">
                <img src="<%=contextPath%>/resources/logo.png" width="100px" height="100px">
            </a>
        </div>	
    </header>
    <main>
    <div id="bg" style="background-image: url('../resources/bg2.jpg');">
        <section class="plan-options">
            <h1 id="topic">Choose Your Plan</h1>
            <div class="option">
                <h2>Become Culer</h2>
                <p>Access basic features.</p>
                <a href="<%=contextPath%>/pages/viewPlan.jsp">Select</a>
            </div>
                <div class="option">
                    <h2>Become Logged<br> In Culer</h2>
                    <p>Access premium features.</p>
                    <a href="<%=contextPath%>/pages/viewPlan.jsp">Select</a>
                </div>
           
        </section>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 FC Barcelona. All rights reserved.</p>
    </footer>
</body>
</html>

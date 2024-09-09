<%@page import="model.PlayersModel"%>
<%@page import="controller.database.dbController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
String username = (String) request.getSession().getAttribute("email");
String role = (String) request.getSession().getAttribute("role");
dbController controller = new dbController();

ArrayList<PlayersModel> playersList = controller.getPlayers();
// Get the username part
String displayUsername = "";

if (username != null) {
	String[] parts = username.split("@");
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
<title>FC Barcelona</title>

<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/stylesheets/home.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>

<body>
	<header>
		<div id="navbar1">

			<%
			if (username != null) {
			%>
			<a href="<%=contextPath%>/pages/userProfile.jsp"><span
				style="COLOR: white;"> Hi, <span style="COLOR: yellow;"><%=displayUsername%>
						!</span>
			</span></a>
			<%
			}
			%>
			<%
			
			String email = (String) request.getSession().getAttribute("email");
			if ("admin".equals(controller.getUserRole(email))) {
			%>
			<a href="<%=contextPath%>/pages/ManagePlayers.jsp"><span
				style="COLOR: white;">Manage Players</span></a>
			<%
			}
			%>
			<a href="<%=contextPath%>/pages/Home.jsp">Home</a> <a
				href="<%=contextPath%>/pages/userProfile.jsp">Profile</a> <a
				href="<%=contextPath%>/pages/viewPlan.jsp"><button id="ViewPlan">View
					Plan</button></a>
		</div>
		<div id="navbar2">
			<a id="BarcaLogo" href="<%=contextPath%>/pages/Home.jsp"> <img
				src="../resources/logo.png" width="100px" height="100px"></a> 
			<div class="search-container">
				<input type="text"  id="searchInput" class="search-input"  placeholder="Search player's name	">
				<button id="searchButton" class="search-button">Search</button>
			</div>
		</div>
	</header>
	<main>
		<div class="user-data">
        <h2 style="color: red; text-align: center;">Our Players</h2>
        
        <div class="player-container">
        <%
			if (playersList == null || playersList.isEmpty()) {
			%>
			<p>No players available.</p>
			<%
			} else {
			%>
            <% for (PlayersModel player : playersList) { %>
                <div class="player-box">
                    <p><strong>Name:</strong> <%= player.getName() %></p>
                    <p><strong>Age:</strong> <%= player.getAge() %></p>	
                    <p><strong>Height:</strong> <%= player.getHeight()%></p>
                    <p><strong>Nation:</strong> <%= player.getNation() %></p>
                    <p><strong>Position:</strong> <%= player.getPosition() %></p>
                    <p><strong>Main Foot:</strong> <%= player.getPreferredFoot() %></p>
                    <p><strong>Squad Number:</strong> <%= player.getSquadNumber() %></p>
                </div>
            <% } %>
            <% } %>
        </div>
    </div>
	</main>
	<footer>
		<p>&copy; 2024 FC Barcelona. All rights reserved.</p>
	</footer>
</body>
<script>
    document.getElementById("searchButton").addEventListener("click", function() {
        var input, filter, container, playerCard, playerName, i;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        container = document.querySelector(".player-container");
        playerCard = container.querySelectorAll(".player-box");

        for (i = 0; i < playerCard.length; i++) {
            playerName = playerCard[i].querySelectorAll("p")[0]; 
            console.log("Player name:", playerName.innerText); // Debugging: Check player name for each player box

            if (playerName.innerText.toUpperCase().indexOf(filter) > -1) {
                playerCard[i].style.display = "";
            } else {
                playerCard[i].style.display = "none";
            }
        }
    });
</script>
</html>

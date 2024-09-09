<%@page import="utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.text.*"%>
<%@page import="model.PlayersModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.database.dbController"%>
<%@page import="utils.StringUtils"%>

<%
String contextPath = request.getContextPath();
String username = (String) request.getSession().getAttribute("email");
dbController controller = new dbController();
ArrayList<PlayersModel> playersList = controller.getPlayers();
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
	href="<%=contextPath%>/stylesheets/playerManagement.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<script>
	function confirmDelete(name) {
		if (confirm("Are you sure you want to delete this player :  " + name
				+ "?")) {
			return true;
		}
		return false;
	}
	function confirmLogout(logoutURL) {
		if (confirm("Are you sure you want to logout?")) {
			window.location.href = logout
			URL;
		}
	}
    document.getElementById("searchKey").addEventListener("click", function() {
        var input, filter, container, playerName, playerUsername, i;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        container = document.querySelector(".user-data");
        playerBoxes = container.querySelectorAll(".player-box");

        for (i = 0; i < playerBoxes.length; i++) {
            playerUsername = playerBoxes[i].querySelectorAll("p")[1]; // Second <p> element contains username
            if (playerUsername.innerText.toUpperCase().indexOf(filter) > -1) {
                playerBoxes[i].style.display = "";
            } else {
                playerBoxes[i].style.display = "none";
            }
        }
    });

</script>
<body>
	<header>
		<div id="navbar1">

			<%
			if (username != null) {
			%>
			<a href="<%=contextPath%>/pages/userProfile.jsp"><span
				style="COLOR: white;"> Hi, <span style="COLOR: yellow;"><%=displayUsername%>
						!</span>
			</span></a>>
			<%
			}
			%>
			<a href="<%=contextPath%>/pages/Home.jsp">Home</a> <a href="#"
				onclick="return confirmLogout('<%=contextPath%>/index.jsp')">Logout</a>
		</div>
		<div id="navbar2">
			<a id="BarcaLogo" href="<%=contextPath%>/pages/Home.jsp"><img
				src="<%=contextPath%>/resources/logo.png" width="100px"
				height="100px"></a>
			<div class="search-container">
				<form action="<%=contextPath%>/SearchPage" method="post">
					<input type="text" name="searchKey" placeholder="Enter player name">
					<button type="submit">
						<i class="fas fa-search"></i>
					</button>
				</form>
			</div>
		</div>
	</header>
	<main>
		<section class="create-player">
			<h2>Create New Player</h2>
			<form action="<%=contextPath%>/AddPage" method="POST">
				<input type="text" name="Name" placeholder=" Name" required>
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
				</select> <select name="Nation" required>
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
				</select> <input type="text" name="Age" placeholder="Age" required> <input
					type="text" name="Height" placeholder="Height" required> <input
					type="text" name="Weight" placeholder="Weight" required> <select
					name="PreferredFoot" required>
					<option value="" disabled selected>Preferred foot</option>
					<option value="United States">Left-footed</option>
					<option value="China">Right-footed</option>
				</select> <input type="text" name="SquadNumber" placeholder="SquadNumber"
					required><br> <input type="file" name="photo"
					accept="image/*">
				<%
				String err = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
				if (err != null) {
				%>
				<p style="color: red;"><%=err%></p>
				<%
				}
				%>
				<button type="submit">Create</button>
			</form>
		</section>

		<section class="player-list">
			<h2>Player List</h2>
			<%
			if (playersList == null || playersList.isEmpty()) {
			%>
			<p>No players found.</p>
			<%
			} else {
			%>
			<table>
				<thead>
					<tr>
						<th>Photo</th>
						<th>Name</th>
						<th>Position</th>
						<th>Nation</th>
						<th>Age</th>
						<th>Height</th>
						<th>Weight</th>
						<th>PreferredFoot</th>
						<th>SquadNumber</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!-- Iterate over player list -->
					<%
					for (PlayersModel players : playersList) {
					%>
					<tr>
						<td><img src="../resources/user.jpg" alt="Player 1"
							width="100px" height="100px"></td>
						<td><%=players.getName()%></td>
						<td><%=players.getPosition()%></td>
						<td><%=players.getNation()%></td>
						<td><%=players.getAge()%></td>
						<td><%=players.getHeight()%></td>
						<td><%=players.getWeight()%></td>
						<td><%=players.getPreferredFoot()%></td>
						<td><%=players.getSquadNumber()%></td>
						<td>
						<a	href="<%=contextPath%>/pages/editPlayer.jsp?squadNo=<%=players.getSquadNumber()%>">Edit</a>

							<form action="<%=contextPath%>/DeletePage" method="post">
								<input type="hidden" name="deleteId"
									value="<%=players.getSquadNumber()%>">
								<button type="submit"
									onclick="return confirmDelete('<%=players.getSquadNumber()%>')">
									Delete
									<%=players.getName()%></button>
							</form></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			}
			%>
		</section>

	</main>
	<footer>
		<p>&copy; 2024 FC Barcelona. All rights reserved.</p>
	</footer>
</body>
</html>

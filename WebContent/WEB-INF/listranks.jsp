<%@ page import="entities.Rank" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
	<%
		ArrayList<Rank> ranks = (ArrayList<Rank>)request.getAttribute("ranks");
	%>
	<title>Ajustes de rango</title>
</head>
<body>
	<header class="container">
		<nav>
			<ul>
				<a href="index.jsp"><li><strong>TpJava WebQuizApp</strong></li></a>
			</ul>
		</nav>
	</header>
    <main class="container">
    <h3>Modificar rango</h3>
    	<% if (!ranks.isEmpty()) { %>
	    	<% for (Rank rank : ranks) { %>
	    		<a href="rank?id=<%= rank.getId() %>"><p><%= rank.getName() %></p></a>
	    	<% } } else { %>
	    	<article><strong>No hay rangos registrados.</strong></article>
	    <% } %>
        <hr />
        <h3>Crear rango</h3>
        <form action="rank" method="post">
			<input type="hidden" name="action" value="create">
			<label>Nombre del rango<input name="name" placeholder="Nombre del rango" required/></label>
			<label>Cantidad de desafíos<input name="amountChallenges" min="0" placeholder="Cantidad de desafíos" type="number" required/></label>
			<button type="submit">
				Crear
			</button>
        </form>
    </main>
</body>
</html>
<%@ page import="entities.Rank" %>

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
		Rank rank = (Rank)request.getAttribute("rank");
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
        <form action="rank" method="post">
        	<input type="hidden" name="action" value="modify">
        	<input type="hidden" name="id" value="<%= rank.getId() %>">
			<label>Nombre del rango<input name="name" placeholder="Nombre del rango" value="<%= rank.getName() %>" required/></label>
			<label>Cantidad de desafíos<input name="amountChallenges" placeholder="Cantidad de desafíos" value="<%= rank.getAmountChallenges() %>" type="number" required/></label>
			<button type="submit">
				Modificar
			</button>
        </form>
        <hr />
        <h3>Eliminar rango</h3>
        <form action="rank" method="post">
        	<input type="hidden" name="action" value="delete">
        	<input type="hidden" name="id" value="<%= rank.getId() %>">
			<button type="submit" style="background-color: #D93526; border-color:#D93526">
				Eliminar rango
			</button>
        </form>
    </main>
</body>
</html>
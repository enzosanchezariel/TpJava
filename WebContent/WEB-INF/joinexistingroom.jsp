<%@ page import="entities.User" %>
<%@ page import="entities.Room" %>

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
		User usr = (User)request.getSession().getAttribute("user");
		Room room = (Room)request.getAttribute("room");
	%>
	<title>Unirse a una sala</title>
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
			<article>
				<strong>La sala con código <%=room.getCode()%> ya existe</strong>
				<p>El nombre es <%=room.getName()%></p>
				<p>¿Desea ingresar?</p>
				<footer>
					<form action="room" method="post" style="display:contents;">
						<input type="hidden" value="<%=room.getCode()%>" name="code">
						<button style="width: 45%; margin:30px;">Aceptar</button>
					</form>
					<a href=createRoom >
						<button style="width: 45%; margin:30px; background-color:grey">Volver</button>
					</a>
				</footer>
			</article>
	</main>
</body>
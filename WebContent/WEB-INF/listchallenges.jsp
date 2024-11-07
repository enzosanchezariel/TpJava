<%@page import="logic.TopicLogic"%>
<%@ page import="entities.Challenge" %>
<%@ page import="entities.Topic" %>
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
		ArrayList<Challenge> challenges = (ArrayList<Challenge>)request.getAttribute("challenges");
		TopicLogic topicLogic = new TopicLogic();
		ArrayList<Topic> topics = topicLogic.getAllNotDisabled();
	%>
	<title>Ajustes de desafío</title>
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
    <h3>Modificar desafío</h3>
    	<% if (!challenges.isEmpty()) { %>
	    	<% for (Challenge challenge : challenges) { %>
	    		<a href="challenge?id=<%= challenge.getIdChallenge() %>"><p><%= challenge.getNameChallenge() %></p></a>
	    	<% } %>
	   	<% } else { %>
	    	<article>
	    		<strong>No hay desafíos registrados</strong>
	    	</article>
	    <% } %>
        <hr />
        <h3>Crear desafío</h3>
        <% if (!topics.isEmpty()) { %>
	        <form action="challenge" method="post">
				<input type="hidden" name="action" value="create">
				<label>Nombre del desafío<input name="name" placeholder="Nombre del desafío" required/></label>
				<label>Tema del desafío
					<select name="topic" aria-label="Selecciona el tema" required>
						<option selected disabled value="">
							Selecciona el tema
						</option>
						<% for(Topic topic: topics) { %>
							<option value="<%= topic.getId() %>"><%= topic.getName() %></option>
						<% } %>
					</select>
				</label>
				<label>Cantidad de respuestas correctas
					<input name="amountQuestions" type="number" min="1" placeholder="Cantidad de respuestas correctas" required>
				</label>
				<button type="submit">Crear</button>
	        </form>
	    <% } else { %>
	    	<article>
	    		<strong>No puede crear desafíos</strong>
	    		<footer>Necesita al menos un tema registrado.</footer>
	    	</article>
	    <% } %>
    </main>
</body>
</html>
<%@ page import="entities.Challenge" %>
<%@ page import="entities.Topic" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="logic.TopicLogic" %>

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
		Challenge challenge = (Challenge)request.getAttribute("challenge");
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
        <form action="challenge" method="post">
        	<input type="hidden" name="action" value="modify">
        	<input type="hidden" name="id" value="<%= challenge.getIdChallenge() %>">
        	
            <label>Nombre del desafío<input name="name" value="<%= challenge.getNameChallenge() %>" placeholder="Nombre del desafío" required/></label>
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
				<input name="amountQuestions" type="number" placeholder="Cantidad de respuestas correctas" required>
			</label>
			<button type="submit">Modificar</button>
        </form>
        <hr />
        <h3>Eliminar desafío</h3>
        <form action="challenge" method="post">
        	<input type="hidden" name="action" value="delete">
        	<input type="hidden" name="id" value="<%= challenge.getIdChallenge() %>">
			<button type="submit" style="background-color: #D93526; border-color:#D93526">
				Eliminar desafío
			</button>
        </form>
    </main>
</body>
</html>
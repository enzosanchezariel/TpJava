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
		ArrayList<Topic> topics = (ArrayList<Topic>)request.getAttribute("topics");
	%>
	<title>Ajustes de tema</title>
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
    <h3>Modificar tema</h3>
    	<% for (Topic topic : topics) { %>
    		<a href="topic?id=<%= topic.getId() %>"><p><%= topic.getName() %></p></a>
    	<% } %>
        <hr />
        <h3>Crear tema</h3>
        <form action="topic" method="post">
			<input type="hidden" name="action" value="create">
			<label>Nombre del tema<input name="name" placeholder="Nombre del tema" required/></label>
			<button type="submit">
				Crear
			</button>
        </form>
    </main>
</body>
</html>
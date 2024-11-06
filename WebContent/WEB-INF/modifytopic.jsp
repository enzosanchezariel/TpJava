<%@ page import="entities.Topic" %>

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
		Topic topic = (Topic)request.getAttribute("topic");
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
        <form action="topic" method="post">
        	<input type="hidden" name="action" value="modify">
        	<input type="hidden" name="id" value="<%= topic.getId() %>">
            <fieldset role="group">
                   <input name="name" placeholder="Nombre" autocomplete="given-name" value="<%= topic.getName() %>" required/>
                   <button type="submit">Aplicar</button>
            </fieldset>
        </form>
        <hr />
        <h3>Eliminar tema</h3>
        <form action="topic" method="post">
        	<input type="hidden" name="action" value="delete">
        	<input type="hidden" name="id" value="<%= topic.getId() %>">
			<button type="submit" style="background-color: #D93526; border-color:#D93526">
				Eliminar tema
			</button>
        </form>
    </main>
</body>
</html>
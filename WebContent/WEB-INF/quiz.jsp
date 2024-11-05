<%@page import="entities.Question"%>
<%@page import="entities.Participation"%>
<%@page import="entities.Quiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Quiz</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"/>
		<%
			Quiz quiz = (Quiz)request.getAttribute("quiz");
			Participation participation = (Participation)request.getSession().getAttribute("attempt");
		%>
	</head>
	<body>
		<header class="container">
			<nav>
				<ul>
					<li><strong>TpJava WebQuizApp</strong></li>
				</ul>
			</nav>
		</header>
		<main class="container">
			<h3><%= quiz.getName() %></h3>
			<form action="quiz" method="post">
				<% int questionIndex = 0; %>
				<% for (Question question : quiz.getQuestions()) { %>
					<article>
						<p><%= question.getQuestionText() %></p>
						<footer>
							<fieldset>
								<legend>Seleccione una opción:</legend>
								<% int optionIndex = 0; %>
								<% for (String option : question.getOptions()) { %>
									<label>
										<input type="radio" name="<%= questionIndex %>" value="<%= optionIndex %>" required/>
										<%= option %>
									</label>
									<% optionIndex = optionIndex + 1; %>
								<% } %>
							</fieldset>
						</footer>
					</article>
					<% questionIndex = questionIndex + 1; %>
				<% } %>
				<button type="submit">Terminar</button>
			</form>
		</main>
	</body>
</html>
<%@page import="java.util.ArrayList"%>

<%@page import="entities.Response"%>
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
			ArrayList<Response> quizResponses = (ArrayList<Response>)request.getAttribute("result");
		%>
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
			<h3>Resultado</h3>
				<% for (Response quizResponse : quizResponses) { %>
					<article>
						<p><%= quizResponse.getQuestion().getQuestionText() %></p>
						<footer>
							<fieldset>
								<% int optionIndex = 1; %>
								<% for (String option : quizResponse.getQuestion().getOptions()) { %>
									<label>
										<input type="radio"
										<% if (optionIndex == quizResponse.getUserResponse()) { %> checked <% } %>
										disabled/>
										<% if (optionIndex == quizResponse.getQuestion().getCorrectAnswer()) { %>
											<u> <%= option %> </u> (Opción correcta)
										<% } else {%>
											<%= option %>
										<% } %>
									</label>
									<% optionIndex = optionIndex + 1; %>
								<% } %>
							</fieldset>
						</footer>
					</article>
				<% } %>
				<a href="room?id=${room}"><button>Volver a la sala</button></a>
		</main>
	</body>
</html>
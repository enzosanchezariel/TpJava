<%@ page import="entities.User" %>
<%@ page import="entities.Topic" %>
<%@ page import="entities.Room" %>
<%@ page import="logic.TopicLogic" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
	<%
		User usr = (User)request.getSession().getAttribute("user");
		TopicLogic topicLogic = new TopicLogic();
		ArrayList<Topic> topics = topicLogic.getAll();
		Room room = (Room)request.getAttribute("room");
	%>
	<title>Crear un quiz</title>
    <script>
        // Función para validar que si hay una pregunta escrita, se deben completar todas las opciones y la respuesta correcta.
        function validateQuizForm() {
            const questionInputs = document.querySelectorAll('.question-input');
            for (let i = 0; i < questionInputs.length; i++) {
                const question = questionInputs[i].value.trim();
                const option1 = document.getElementById('option1-' + i).value.trim();
                const option2 = document.getElementById('option2-' + i).value.trim();
                const option3 = document.getElementById('option3-' + i).value.trim();
                const option4 = document.getElementById('option4-' + i).value.trim();
                const correct = document.getElementById('correct-' + i).value;

                if (question !== "" && (option1 === "" || option2 === "" || option3 === "" || option4 === "" || correct === "")) {
                    alert("Por favor completa todas las opciones y selecciona la respuesta correcta para la pregunta " + (i + 1));
                    return false;  // Cancela el envío del formulario si hay un error.
                }
            }
            return true;  // Permite el envío si todo está completo.
        }
    </script>
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
    <h2>Crear un quiz</h2>
    <% if(!topics.isEmpty()) { %>
        <form action="createQuiz" method="post" onsubmit="return validateQuizForm();">
            <article>
                <fieldset>
                    <label>Nombre
                        <input name="name" placeholder="Nombre" autocomplete="given-name"  required/>
                    </label>
                    <label>Duración
                        <input type="time" name="maxDuration" placeholder="Duración" required/>
                    </label>
                    
                    <label>Tema
	                    <select name="topicId" aria-label="Selecciona el tema" required>
						    <option selected disabled value="">
						        Selecciona el tema
						    </option>
						        <% for(Topic aTopic: topics){ %>
						            <% if (!aTopic.isDeleted()) { %>
						                <option value="<%= aTopic.getId() %>"><%= aTopic.getName() %></option>
						            <% } %>
						        <% } %>
						</select>
					</label>
                </fieldset>
    		</article>
    		<h3>Preguntas</h3>
    		<small>Puedes hacer hasta 10 preguntas</small>
	        <% for (int i = 0; i < 10; i++) { %>
			    <article>
			        <div class="question-block">
			            <h4><label for="question<%= i %>">Pregunta <%= i + 1 %></label></h4>
			            <input type="text" class="question-input" name="question<%= i %>" id="question<%= i %>" 
			                   <% if(i == 0) { %> required <% } %>><br>
			            
			            <label for="option1-<%= i %>">Opción 1</label>
			            <input type="text" name="option1-<%= i %>" id="option1-<%= i %>"><br>
			
			            <label for="option2-<%= i %>">Opción 2</label>
			            <input type="text" name="option2-<%= i %>" id="option2-<%= i %>"><br>
			
			            <label for="option3-<%= i %>">Opción 3</label>
			            <input type="text" name="option3-<%= i %>" id="option3-<%= i %>"><br>
			
			            <label for="option4-<%= i %>">Opción 4</label>
			            <input type="text" name="option4-<%= i %>" id="option4-<%= i %>"><br>
			
			            <label for="correct-<%= i %>">Respuesta correcta</label>
			            <select name="correct-<%= i %>" id="correct-<%= i %>">
			                <option value="">Seleccionar</option>
			                <option value="1">Opción 1</option>
			                <option value="2">Opción 2</option>
			                <option value="3">Opción 3</option>
			                <option value="4">Opción 4</option>
			            </select>
			            <br><br>
			        </div>
			    </article>
			<% } %>
	        <input type="hidden" name="room" value="<%=room.getCode()%>">
        	<button type="submit">Guardar Preguntas</button>
    	</form>
   	<% } else{ %>
   		<strong>No puede crear quizzes</strong>
	    <footer>Necesita al menos un tema registrado.</footer>
	<% } %>    
    </main>
    
</body>
</html>
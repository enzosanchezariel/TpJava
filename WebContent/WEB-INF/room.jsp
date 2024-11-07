<%@page import="entities.Participation"%>
<%@page import="logic.ParticipationLogic"%>
<%@ page import="entities.Room" %>
<%@ page import="entities.User" %>
<%@ page import="entities.Quiz" %>
<%@ page import="entities.RankedUser" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
	<link rel="stylesheet" type="text/css" href="style/room.css" />
	
	<%
		Room room = (Room)request.getAttribute("room");
		User usr = (User)request.getSession().getAttribute("user");
		List<RankedUser> rankedUsers = (List<RankedUser>) request.getAttribute("rankedUsers"); 
		ParticipationLogic participationLogic = new ParticipationLogic();
	%>
	
	<script type="text/javascript">
		function confirmLeave() {
	        if (confirm(
	        		<% if (room.getAdmin() != null && usr.getId() == room.getAdmin().getId()) {%>
						"¿Estás seguro de que deseas borrar la sala? Esta acción no se puede deshacer."
					<% } else { %>
						"¿Estás seguro de que deseas salir de la sala?"
					<% } %>
	        		)) {
	            document.getElementById("leaveForm").submit();
	        }
	    }
	</script>
	
	<title><%=room.getName()%></title>
</head>
<body>
	<header class="container">
		<nav>
			<ul>
				<a href="index.jsp"><li><strong>TpJava WebQuizApp</strong></li></a>
			</ul>
				<ul>
					<li>
	                    <details class="dropdown">
					        <summary>
					          Cuenta
					        </summary>
					        <ul dir="rtl">
					          <li>
					          	<form action="session" method="post">
				                    <button name="action" value="logout" type="submit" data-placement="bottom" class="dropdown-button">
				                        Cerrar sesión
				                    </button>
			                    </form>
					          </li>
					          <li>
					          	<a href="account" class="secondary">
				                	<button type="submit" class="dropdown-button">
					                        Ajustes de usuario
					                </button>
				                </a>
					          </li>
					        </ul>
				      </details>
	                </li>
                </ul>
			</nav>
	</header>
	<main class="container">		
		<h1><%=room.getId()%> | <%=room.getName()%></h1>
           <div class="flex">
               <div class="chip">
                   <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-calendar-event"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M4 5m0 2a2 2 0 0 1 2 -2h12a2 2 0 0 1 2 2v12a2 2 0 0 1 -2 2h-12a2 2 0 0 1 -2 -2z" /><path d="M16 3l0 4" /><path d="M8 3l0 4" /><path d="M4 11l16 0" /><path d="M8 15h2v2h-2z" /></svg>
                   <strong><%=room.getInitDateAsString()%> al <%=room.getEndDateAsString()%></strong></div>
               <div class="chip">
                   <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-users"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M9 7m-4 0a4 4 0 1 0 8 0a4 4 0 1 0 -8 0" /><path d="M3 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" /><path d="M16 3.13a4 4 0 0 1 0 7.75" /><path d="M21 21v-2a4 4 0 0 0 -3 -3.85" /></svg>
                   <strong>Participantes: <%=room.getAmountParticipants()%>/<%=room.getMaxAmountParticipants()%></strong></div>
               <% if (room.getAdmin() != null) {%>
	               <div class="chip">
	                   <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-users"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M13.163 2.168l8.021 5.828c.694 .504 .984 1.397 .719 2.212l-3.064 9.43a1.978 1.978 0 0 1 -1.881 1.367h-9.916a1.978 1.978 0 0 1 -1.881 -1.367l-3.064 -9.43a1.978 1.978 0 0 1 .719 -2.212l8.021 -5.828a1.978 1.978 0 0 1 2.326 0z" /><path d="M12 13a3 3 0 1 0 0 -6a3 3 0 0 0 0 6z" /><path d="M6 20.703v-.703a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v.707" /></svg>
	                   <strong>Admin: <%=room.getAdmin().getName()%> <%=room.getAdmin().getSurname()%></strong></div>
               <% } %>
               <% if (room.getAdmin() != null && room.getAdmin().getId() == usr.getId()) {%>
	               <div class="chip">
	               		<svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-lock"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 13a2 2 0 0 1 2 -2h10a2 2 0 0 1 2 2v6a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-6z" /><path d="M11 16a1 1 0 1 0 2 0a1 1 0 0 0 -2 0" /><path d="M8 11v-4a4 4 0 1 1 8 0v4" /></svg>
	                   <strong>Código: <%=room.getCode()%></strong></div>
               <% } %>
           </div>
           <hr/>
		<h3>Formularios disponibles</h3>
           <% for (Quiz quiz : room.getQuizzes()) { if (!quiz.isDeleted()){%>
           <% Participation participation = participationLogic.getParticipationByUserAndQuiz(usr, quiz); %>
            <a href="quiz?id=<%= quiz.getId() %>" class="secondary no-underline">
                <article>
                    <strong><%= quiz.getName() %></strong>
	                <footer>
	                    <div class="flex">
	                        <div class="chip">
	                            <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-triangle"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M10.363 3.591l-8.106 13.534a1.914 1.914 0 0 0 1.636 2.871h16.214a1.914 1.914 0 0 0 1.636 -2.87l-8.106 -13.536a1.914 1.914 0 0 0 -3.274 0z" /></svg>
	                            <strong>Tema: <%= quiz.getTopic().getName() %></strong></div>
	                        <div class="chip">
	                            <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-hourglass-empty"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M6 20v-2a6 6 0 1 1 12 0v2a1 1 0 0 1 -1 1h-10a1 1 0 0 1 -1 -1z" /><path d="M6 4v2a6 6 0 1 0 12 0v-2a1 1 0 0 0 -1 -1h-10a1 1 0 0 0 -1 1z" /></svg>
	                            <strong>Duración: <%= quiz.getMaxDurationAsString() %></strong></div>
	                    	<% if (participation != null) { %>
		                    	<div class="chip">
		                    		<svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-check"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 12l5 5l10 -10" /></svg>
		                            <strong>Completado: <%= participation.getAmountRight() %> puntos</strong></div>
		                    	</div>
	                    	<% } %>
	                    	</div>
	                </footer>
            	</article>
         	</a>
         	
         	<% if (room.getAdmin() != null && usr.getId() == room.getAdmin().getId()) {%>
				<form action="removequiz" method="post" id="removeQuiz">
					<input type="hidden" name="id" value="<%=quiz.getId()%>">
					<button type="submit" style="background-color: #D93526; border-color:#D93526">Eliminar Quiz</button>
				</form>
			<% } %>
			
        <% } } %>
        <% if (room.getQuizzes().isEmpty()) { %>
   			<article>
   				<strong>No hay formularios</strong>
   			</article>
   		<% } %>
   		<% if (room.getAdmin() != null) { %>
	   		<% if (usr.getId() == room.getAdmin().getId()){ %>
	   			<form action="createquiz" method="get">
	   					<input type="hidden" name="room" value="<%=room.getCode()%>">
	               		<button type="submit">Crear Quiz</button>
	            </form>
	   		<% } %>
	   	<% } %>
	<details>
    	<summary role="button">Mostrar Ranking</summary>
    	<table>
        	<thead>
            	<tr>
                	<th>Posición</th>
                	<th>Nombre</th>
                	<th>Puntos</th>
            	</tr>
        	</thead>
        	<tbody>
            	<% 
            	int position = 1;  
            	if (rankedUsers != null && !rankedUsers.isEmpty()) { 
            	%>
                	<% for (RankedUser rankedUser : rankedUsers) { %>
                    	<tr>
                        	<td><%= position++ %></td>
                        	<td><%= rankedUser.getUser().getName() %> <%= rankedUser.getUser().getSurname() %></td>
                        	<td><%= rankedUser.getTotalPoints() %></td>
                    	</tr>
                	<% } %>
            	<% } else { %>
                	<tr>
                    	<td colspan="3">No hay usuarios en el ranking</td>
                	</tr>
            	<% } %>
        	</tbody>
    	</table>
	</details>
	
	<form action="leavedeleteroom" method="post" id="leaveForm">
		<input type="hidden" name="id" value="<%= room.getId() %>">
	</form>
   	
	<button type="button" onclick="confirmLeave()" style="background-color: #D93526; border-color:#D93526">
		<% if (room.getAdmin() != null && usr.getId() == room.getAdmin().getId()) {%>
			Eliminar sala
		<% } else { %>
			Salir de la sala
		<% } %>
	</button>
   	
	</main>

</body>
</html>
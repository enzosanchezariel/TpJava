<%@ page import="entities.User" %>
<%@ page import="entities.Room" %>
<%@ page import="logic.RoomLogic" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>WebQuizApp</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"/>
		<link rel="stylesheet" href="style/index.css" />
		<%
		User usr = (User)request.getSession().getAttribute("user");
		//ArrayList<Room> rooms = null;
		if (usr != null) {
			RoomLogic roomLogic = new RoomLogic();
			usr.setRooms(roomLogic.getRoomsByUser(usr));
		}
		%>
	</head>
	<body>
		<header class="container">
			<nav>
				<ul>
					<li><strong>TpJava WebQuizApp</strong></li>
				</ul>
				<ul>
				<%if(usr != null){%>
					<li>
						<form action="session" method="post">
		                    <button name="action" value="logout" class="outline" type="submit" data-tooltip="Cerrar sesión" data-placement="bottom" style="padding: 0.4rem; margin: 0px;">
		                        <strong><%=usr.getName()%></strong>
		                    </button>
	                    </form>
	                </li>
                <%}else{%>
	                <li><a href="login.html" class="secondary">
	                	<button type="submit" style="padding: 0.4rem; margin: 0px;">
		                        <strong>Iniciar sesión</strong>
		                </button>
	                </a>
	                <a href="signup.html" class="secondary">
	                	<button type="submit" style="padding: 0.4rem; margin: 0px;">
		                        <strong>Registrarse</strong>
		                </button>
	                </a>
	                </li>
                <%}%>
                </ul>
			</nav>
		</header>
		<main class="container">
			<% if (usr != null){%>
			<h3>Añadir sala</h3>
	            <div class="grid">
	                <div>
	                	<form action="room" method="post">
		                    <article>
		                        <strong>Únete a una sala</strong>
		                        <footer>
		                            <input type="text" name="code" placeholder="Código" aria-label="Text"/>
		                            <button type="submit" style="padding: 0.4rem; margin: 0px; width:30%;">Buscar</button>
		                        </footer>
		                    </article>
		            	</form>
	                </div>
	                <div>
	                    <article>
	                        <strong>Crea una sala</strong>
	                        <footer><button type="submit" style="padding: 0.4rem; margin: 0px; width:100%;">Crear</button></footer>
	                    </article>
	                </div>
	            </div>
	            <hr />
				<h3>Salas disponibles</h3>
	            <div class="responsive-grid">
	            	<% for (Room room : usr.getRooms()) { if (!room.isDeleted()){%>
	            	<a href="room?id=<%=room.getId()%>" class="secondary no-underline">
						<article>
							<div class="room-title">
								<div class="row">
									<p class="no-underline"><%= room.getId() %></p>
										<strong><%= room.getName() %></strong>
								</div>
							</div>
							<footer>
								<div class="room-details">
									<div class="row participants">
										<svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-users"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M9 7m-4 0a4 4 0 1 0 8 0a4 4 0 1 0 -8 0" /><path d="M3 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" /><path d="M16 3.13a4 4 0 0 1 0 7.75" /><path d="M21 21v-2a4 4 0 0 0 -3 -3.85" /></svg>
										<strong><%= room.getAmountParticipants() %>/<%= room.getMaxAmountParticipants() %></strong>
									</div>
									<p class="no-underline">Vencimiento: <%= room.getEndDateAsString() %></p>
								</div>
							</footer>
						</article>
					</a>
					<% }} %>
				</div>
	        <%}else{%>
	        	<article>
	        		<strong>Inicie sesión</strong>
	        		<footer>Para crear o unirse a una sala, debe tener una cuenta e iniciar sesión.</footer>
	        	</article>
	        <%}%>
		</main>
	</body>
</html>
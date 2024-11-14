<%@page import="logic.TopicLogic"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Room" %>
<%@ page import="logic.RoomLogic" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import= "entities.Challenge" %>
<%@ page import="logic.ChallengeLogic" %>
<%@ page import ="entities.Rank" %>
<%@ page import="logic.RankLogic" %>


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
		ArrayList<Challenge> challenges = null;
		TopicLogic topicLogic = new TopicLogic();
		Rank rank = null;
		//ArrayList<Room> rooms = null;
		if (usr != null) {
			RoomLogic roomLogic = new RoomLogic();
			usr.setRooms(roomLogic.getRoomsByUser(usr));
			ChallengeLogic challengeLogic = new ChallengeLogic();
			challenges = challengeLogic.challengeByUserId(usr);
			RankLogic rankLogic = new RankLogic();
			rank = rankLogic.getRankByUserId(usr);
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
	                    <details class="dropdown">
					        <summary>
					          Cuenta
					        </summary>
					        <ul dir="rtl">
					          <li>
					          	<a>
					          	<form action="session" method="post">
				                    <button name="action" value="logout" type="submit" data-placement="bottom" class="dropdown-button">
				                        Cerrar sesión
				                    </button>
			                    </form>
			                    </a>
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
                <%}else{%>
	                <li>
	                    <details class="dropdown">
					        <summary>
					          Ingresar
					        </summary>
					        <ul dir="rtl">
					          <li>
					          	<a href="login.html" class="secondary">
				                	<button type="submit" class="dropdown-button">
					                        Iniciar sesión
					                </button>
				                </a>
					          </li>
					          <li>
					          	<a href="signup.html" class="secondary">
				                	<button type="submit" class="dropdown-button">
					                        Registrarse
					                </button>
				                </a>
					          </li>
					        </ul>
				      </details>
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
	                    <article>
	                        <strong>Únete a una sala</strong>
	                        <footer>
	                        	<form action="room" method="post">
	                        		<fieldset role="group">
	                            		<input type="text" maxlength="36" name="code" placeholder="Código" aria-label="Text"/>
	                            		<button type="submit">Ingresar</button>
	                            	</fieldset>
	                        	</form>
	                        </footer>
	                    </article>
	                </div>
	                <div>
	                    <article>
	                        <strong>Crea una sala</strong>
	                        <footer>
	                        	<form action="createroom" method="get">
	                        		<button type="submit">Crear</button>
	                        	</form>
                        	</footer>
	                    </article>
	                </div>
	            </div>
	            <hr/>
	            <h3>Tu Rango es: </h3>
	            <% if (rank != null) { %>
	    		<span><%= rank.getName() %></span> 
	    	<% } %>
	   	<% } else { %>
	    	<article>
	    		<strong>No tienes rango</strong>
	    	</article>
	    <% } %>
	            <h3>Desafios Realizados</h3>
			    	
			    	<details>
				    	<summary role="button">Mostrar Desafios</summary>
				    	<table>
				        	<thead>
				            	<tr>
				                	<th>Nombre</th>
				                	<th>Tema</th>
				            	</tr>
				        	</thead>
				        	<tbody>
				        		<% if (!challenges.isEmpty()) { %>
				                	<% for (Challenge challenge : challenges) { %>
				                    	<tr>
				                        	<td><%= challenge.getNameChallenge() %></td>
				                        	<td><%= topicLogic.getById(challenge.getTopic()).getName() %></td>
				                    	</tr>
				                    <% } %>
				            	<% } else { %>
				                	<tr>
				                    	<td colspan="3">No completó ningún desafío</td>
				                	</tr>
				            	<% } %>
				        	</tbody>
				    	</table>
					</details>
			    	
	            <h3>Salas disponibles</h3>
	            <div class="responsive-grid">
	            	<% for (Room room : usr.getRooms()) { if (!room.isDeleted()) {%>
	            	<a href="room?id=<%=room.getId()%>" class="secondary no-underline">
						<article>
							<div class="room-title">
								<div class="row">
									<p class="no-underline"><%= room.getId() %></p>
										<strong><%= room.getName() %></strong>
								</div>
							</div>
							<footer>
								<div class="flex">
									<div class="chip">
			                            <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M9 7m-4 0a4 4 0 1 0 8 0a4 4 0 1 0 -8 0" /><path d="M3 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" /><path d="M16 3.13a4 4 0 0 1 0 7.75" /><path d="M21 21v-2a4 4 0 0 0 -3 -3.85" /></svg>
			                            <strong>Participantes: <%= room.getAmountParticipants() %>/<%= room.getMaxAmountParticipants() %></strong></div>	
									<div class="chip">
										<svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M4 5m0 2a2 2 0 0 1 2 -2h12a2 2 0 0 1 2 2v12a2 2 0 0 1 -2 2h-12a2 2 0 0 1 -2 -2z" /><path d="M16 3l0 4" /><path d="M8 3l0 4" /><path d="M4 11l16 0" /><path d="M8 15h2v2h-2z" /></svg>
			                            <strong>Vencimiento: <%= room.getEndDateAsString() %></strong></div>
			                        <% if (!room.isValid()) { %>
			                        	<div class="chip">
											<svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M9 5h9a2 2 0 0 1 2 2v9m-.184 3.839a2 2 0 0 1 -1.816 1.161h-12a2 2 0 0 1 -2 -2v-12a2 2 0 0 1 1.158 -1.815" /><path d="M16 3v4" /><path d="M8 3v1" /><path d="M4 11h7m4 0h5" /><path d="M3 3l18 18" /></svg>
				                            <strong>Sala finalizada</strong></div>
			                        <% } %>
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
	        
	        <% if (usr != null && usr.isAdmin()) { %>
	        	<div class="admin-buttons"><a href="topics"><button>Configurar temas</button></a></div>
	        	<div class="admin-buttons"><a href="challenges"><button>Configurar desafíos</button></a></div>
	        	<div class="admin-buttons"><a href="ranks"><button>Configurar rangos</button></a></div>
	        <% } %>
		</main>
	</body>
</html>
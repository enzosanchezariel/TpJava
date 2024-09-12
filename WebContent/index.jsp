<%@ page import="entities.User" %>
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
		User usr = null;
		if (request.getSession().getAttribute("user") != null) usr = (User)request.getSession().getAttribute("user");
		%>
	</head>
	<body>
		<header class="container">
			<nav>
				<ul>
                    <li><a href="#" class="secondary">
                        <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-menu-2" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="#2c3e50" fill="none" stroke-linecap="round" stroke-linejoin="round">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M4 6l16 0" />
                            <path d="M4 12l16 0" />
                            <path d="M4 18l16 0" />
                        </svg>
                    </a></li>
					<li><strong>TpJava WebQuizApp</strong></li>
				</ul>
				<ul>
				<%if(usr != null){%>
					<li>
						<form action="session" method="post">
		                    <button name="action" value="logout" class="outline" type="submit" data-tooltip="Cerrar sesión" data-placement="bottom" style="padding: 0.4rem; margin: 0px;">
		                        <strong><%=usr.getUser()%></strong>
		                    </button>
	                    </form>
	                </li>
                <%}else{%>
	                <li><a href="login.html" class="secondary">
	                	<button type="submit" style="padding: 0.4rem; margin: 0px;">
		                        <strong>Iniciar sesión</strong>
		                </button>
	                </a></li>
                <%}%>
                </ul>
			</nav>
		</header>
		<main class="container">
			<% if (usr != null){%>
	            <div class="grid">
	                <div>
	                    <article>
	                        <strong>Únete a una sala</strong>
	                        <footer>
	                            <input type="text" name="code" placeholder="Código" aria-label="Text"/>
	                        </footer>
	                    </article>
	                </div>
	                <div>
	                    <article>
	                        <strong>Crea una sala</strong>
	                        <footer><button class="btn">Crear</button></footer>
	                    </article>
	                </div>
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
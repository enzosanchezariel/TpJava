<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>WebQuizApp</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"/>
		<%
		User usr = (User)request.getSession().getAttribute("user");
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
			<!-- Resultado de el/los ganador/es de la sala -->
		</main>
	</body>
</html>
<%@ page import="entities.Room" %>
<%@ page import="entities.User" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
	<%
		Room room = null;
		room = (Room) request.getAttribute("room");
		User usr = null;
		usr = (User)request.getSession().getAttribute("user");
	%>
	<title><%=room.getName()%></title>
</head>
<body>
	<header class="container">
		<nav>
			<ul>
				<li><strong>TpJava WebQuizApp</strong></li>
			</ul>
			<ul>
				<li>
					<form action="session" method="post">
	                    <button name="action" value="logout" class="outline" type="submit" data-tooltip="Cerrar sesión" data-placement="bottom" style="padding: 0.4rem; margin: 0px;">
	                        <strong><%=usr.getName()%></strong>
	                    </button>
                    </form>
                </li>
            </ul>
		</nav>
	</header>
	<main class="container">
			<h1><%=room.getId()%> | <%=room.getName()%></h1>
			<h2><%=room.getAmountParticipants()%>/<%=room.getMaxAmountParticipants()%></h2>
			<h3><%=room.getInitDate()%> al <%=room.getEndDate()%></h3>
			<h1>.</h1>
			<h1>.</h1>
			<h1>.</h1>
			<strong>*Lista de formularios*</strong>
	</main>
</body>
</html>
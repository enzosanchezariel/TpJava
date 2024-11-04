<%@ page import="entities.User" %>

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
		User usr = (User)request.getSession().getAttribute("user");
	%>
	<title>Crear una sala</title>
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
    <h3>Crear una sala</h3>
        <form action="createroom" method="post">
            <article>
                <fieldset>
                    <label>Nombre
                        <input name="name" placeholder="Nombre" autocomplete="given-name"  required/>
                    </label>
                    <label>Cantidad máxima de personas
                        <input type="number" name="maxAmountParticipants" placeholder="Ingrese un número" autocomplete="given-name"  required/>
                    </label>
                    <label>Código
                        <input name="code" maxlength="36" placeholder="Código" autocomplete="given-name"  required/>
                    </label>
                    <label>Fecha de finalización
                        <input type="date" name="endDate" aria-label="Date" min="<%= java.time.LocalDate.now().plusDays(1).toString() %>" required/>
                    </label>
                </fieldset>
                <footer>
                    <button name="action" value="createRoomValidation" type="submit">Crear</button>
                </footer>
            </article>
        </form>
</body>
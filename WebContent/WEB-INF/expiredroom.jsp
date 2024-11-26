<%@page import="entities.RankedUser"%>
<%@page import="java.util.List"%>
<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Resultados de la Sala</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"/>
    <%
    User usr = (User) request.getSession().getAttribute("user");
    %>
</head>
<body>
    <header class="container">
        <nav>
            <ul>
                <li><strong>TpJava WebQuizApp</strong></li>
            </ul>
            <ul>
                <% if (usr != null) { %>
                    <li>
                        <details class="dropdown">
                            <summary>Cuenta</summary>
                            <ul dir="rtl">
                                <li>
                                    <form action="session" method="post">
                                        <button name="action" value="logout" type="submit" class="dropdown-button">Cerrar sesión</button>
                                    </form>
                                </li>
                                <li>
                                    <a href="account" class="secondary">
                                        <button type="submit" class="dropdown-button">Ajustes de usuario</button>
                                    </a>
                                </li>
                            </ul>
                        </details>
                    </li>
                <% } else { %>
                    <li>
                        <details class="dropdown">
                            <summary>Ingresar</summary>
                            <ul dir="rtl">
                                <li>
                                    <a href="login.html" class="secondary">
                                        <button type="submit" class="dropdown-button">Iniciar sesión</button>
                                    </a>
                                </li>
                                <li>
                                    <a href="signup.html" class="secondary">
                                        <button type="submit" class="dropdown-button">Registrarse</button>
                                    </a>
                                </li>
                            </ul>
                        </details>
                    </li>
                <% } %>
            </ul>
        </nav>
    </header>
    <main class="container">
        <article>
            <header>
                <h2>Resultados de la Sala</h2>
            </header>
            <%
            List<RankedUser> winners = (List<RankedUser>) request.getAttribute("winners");
            String roomName = (String) request.getAttribute("roomName");
            if (roomName != null) { 
            %>
                <p>Sala: <strong><%= roomName %></strong></p>
            <% } %>
            <section>
                <% if (winners != null && !winners.isEmpty()) { %>
                    <h3>Ganadores:</h3>
                    <ul>
                        <% for (RankedUser winner : winners) { %>
                            <li>
                                <strong><%= winner.getUser().getName() + " " + winner.getUser().getSurname() %></strong>
                                con <%= winner.getTotalPoints() %> puntos.
                            </li>
                        <% } %>
                    </ul>
                <% } else { %>
                    <p>No hay ganadores registrados en esta sala.</p>
                <% } %>
            </section>
            <footer>
                <a href="index.jsp" class="secondary">Volver al inicio</a>
            </footer>
        </article>
    </main>
</body>
</html>

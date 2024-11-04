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
	<title>Ajustes de usuario</title>
	
	<script type="text/javascript">
		function confirmDelete() {
	        if (confirm("¿Estás seguro de que deseas borrar tu cuenta? Esta acción no se puede deshacer.")) {
	            document.getElementById("deleteForm").submit();
	        }
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
    <h3>Modificar cuenta</h3>
        <form action="account" method="post">
            <article>
                <fieldset>
                    <label>Nombre
                        <input name="name" placeholder="Nombre" autocomplete="given-name" value="<%= usr.getName() %>" required/>
                    </label>
                    <label>Apellido
                        <input name="surname" placeholder="Apellido" autocomplete="family-name" value="<%= usr.getSurname() %>" required/>
                    </label>
                    <label>Email
                        <input type="email" name="email" placeholder="Email" autocomplete="email" value="<%= usr.getEmail() %>" required/>
                    </label>
                    <label>Contraseña nueva
                        <input type="password" name="password" placeholder="Contraseña" value="<%= usr.getPassword() %>" required/>
                    </label>
                    <label>Confirmar contraseña nueva
                        <input type="password" name="confirmPassword" placeholder="Repite la contraseña" value="<%= usr.getPassword() %>" required/>
                    </label>
                </fieldset>
                <footer>
                    <button name="action" value="modifyAccount" type="submit">Enviar cambios</button>
                </footer>
            </article>
        </form>
        <hr />
        <h3>Eliminar cuenta</h3>
        <form action="account" method="post" id="deleteForm">
        	<input type="hidden" name="action" value="deleteAccount">
         <button type="button" onclick="confirmDelete()" style="background-color: #D93526; border-color:#D93526">
             Eliminar cuenta
         </button>
        </form>
    </main>
</body>
</html>
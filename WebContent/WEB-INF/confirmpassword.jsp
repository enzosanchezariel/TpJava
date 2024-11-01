<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Confirme su contraseña</title>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
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
		<h3>Confirme su contraseña para realizar modificaciones a su cuenta</h3>
		<form action="account" method="post">
			<article>
				<fieldset>
					<label>Contraseña<input type="password" name="password"
						placeholder=Contraseña required/>
					</label>
				</fieldset>
				<footer>
					<button name="action" value="confirmAccount" type="submit">Continuar</button>
				</footer>
			</article>
		</form>
	</main>
</body>
</html>
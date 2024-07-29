<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Quiz</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"/>
		<link rel="stylesheet" href="style/question.css" />
	</head>
	<body>
		<header>
			<nav>
				<ul></ul>
				<ul>
					<li><strong>Pregunta 1</strong></li>
				</ul>
				<ul></ul>
			</nav>
		</header>
		<main class="container">
			<form action="question" method="post">
				<article>
					<p>¿Cuáles de los siguientes son planetas del sistema solar?</p>
					<footer>
						<fieldset>
							<legend>Seleccione una o mas opciones:</legend>
							<label>
								<input type="checkbox"/>
								Luna
							</label>
							<label>
								<input type="checkbox"/>
								Jupiter
							</label>
							<label>
								<input type="checkbox"/>
								Marte
							</label>
							<label>
								<input type="checkbox"/>
								Vía Láctea
							</label>
							<label>
								<input type="checkbox"/>
								Joe Biden
							</label>
						</fieldset>
					</footer>
				</article>
			</form>
		</main>
		<footer class="container">
			<nav>
				<ul>
					<li><button class="secondary">Anterior</button></li>
				</ul>
				<ul></ul>
				<ul>
					<li><button>Siguiente</button></li>
				</ul>
			</nav>
		</footer>
	</body>
</html>
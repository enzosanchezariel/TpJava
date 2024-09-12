<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title><%=request.getAttribute("headTitle")%></title>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css" />
	
</head>
<body>
	<main class="container">
			<article>
				<strong><%=request.getAttribute("bodyTitle")%></strong>
				<footer>
					<a href="<%=request.getAttribute("buttonAction")%>"><button style="width: 100%"><%=request.getAttribute("buttonMessage")%></button></a>
				</footer>
			</article>
	</main>
</body>
</html>
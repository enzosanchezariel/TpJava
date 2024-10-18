<%@ page import="java.util.List" %>
<%@ page import="entities.Question" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
    <form action="Quiz" method="post">
        <%
            Object obj = request.getAttribute("questions");
            List<Question> questions = null;
            if (obj instanceof List<?>) {
                questions = (List<Question>) obj;
            }
            
            if (questions != null) {
                for (Question question : questions) {
        %>
                    <p><%= question.getQuestionText() %></p>
                    <%
                        List<String> options = question.getOptions();
                        if (options != null) {
                            for (int j = 0; j < options.size(); j++) {
                    %>
                                <input type="radio" name="<%= question.getQuestionText() %>" value="<%= j %>" /> <%= options.get(j) %><br/>
                    <%
                            }
                        } else {
                    %>
                            <p>No hay opciones disponibles para esta pregunta.</p>
                    <%
                        }
                    %>
        <%
                }
            } else {
        %>
            <p>No hay preguntas disponibles.</p>
        <%
            }
        %>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
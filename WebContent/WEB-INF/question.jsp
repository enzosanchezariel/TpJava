<%@ page import="java.util.List" %>
<%@ page import="entities.Question" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
    <form action="Quiz" method="post">
        <%
            List<Question> questions = (List<Question>) request.getAttribute("questions");
            if (questions != null) {
                for (Question question : questions) {
        %>
                    <p><%= question.getQuestionText() %></p>
                    <%
                        List<String> options = question.getOptions();
                        for (int j = 0; j < options.size(); j++) {
                    %>
                            <input type="radio" name="<%= question.getQuestionText() %>" value="<%= j %>" /> <%= options.get(j) %><br/>
                    <%
                        }
                    %>
        <%
                }
            }
        %>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
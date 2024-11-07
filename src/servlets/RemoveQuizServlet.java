package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Quiz;
import entities.User;
import logic.QuizLogic;
import logic.RoomLogic;

@WebServlet({ "/RemoveQuiz", "/removequiz", "/REMOVEQUIZ", "/removeQuiz" })
public class RemoveQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RemoveQuizServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			} catch (NumberFormatException e) {
				id = -1;
			}
			if (id != -1) {
				Quiz quiz = new Quiz(id);
				QuizLogic quizLogic = new QuizLogic();
				RoomLogic roomLogic = new RoomLogic();
				quiz = quizLogic.getById(quiz);
				quiz.getRoom().setAdmin(roomLogic.getRoomAdmin(quiz.getRoom()));
				if (quiz.getRoom().getAdmin() != null && user.getId() == quiz.getRoom().getAdmin().getId()) {
					quizLogic.delete(quiz);
					response.sendRedirect("room?id=" + quiz.getRoom().getId());
				}
			}
		}
	}
}

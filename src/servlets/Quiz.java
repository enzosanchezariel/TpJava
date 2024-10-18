package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Question;
import logic.QuizLogic;

/**
 * Servlet implementation class Quiz
 */
@WebServlet({ "/Quiz", "/quiz", "/QUIZ" })
public class Quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Quiz() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuizLogic quizLogic = new QuizLogic();

        // Obtener la lista de preguntas a través de QuizLogic
        List<Question> questions = quizLogic.getQuestions();

        // Pasar las preguntas a la JSP
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("/WEB-INF/quiz.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

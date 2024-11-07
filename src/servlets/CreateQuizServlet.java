package servlets;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import logic.OptionLogic;
import logic.QuestionLogic;
import logic.QuizLogic;
import logic.RoomLogic;
import logic.TopicLogic;
import entities.Quiz;
import entities.Room;
import entities.Topic;
import entities.Option;
import entities.Question;


/**
 * Servlet implementation class CreateQuizServlet
 */
@WebServlet({ "/CreateQuizServlet", "/CreateQuiz", "/createquiz", "/CREATEQUIZ", "/createQuiz" })
public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateQuizServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoomLogic roomLogic = new RoomLogic();
		Room room = new Room();
		
		User usr = (User) request.getSession().getAttribute("user");
		
		room.setCode(request.getParameter("room"));
		Room foundRoom = roomLogic.getRoomByCode(room);
		
		
		if(foundRoom == null) {
			request.setAttribute("headTitle", "Acceso denegado");
	        request.setAttribute("bodyTitle", "No se encuentra en una sala");
	        request.setAttribute("buttonAction", "index.jsp");
	        request.setAttribute("buttonMessage", "Aceptar");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
		}
		
		if (usr == null) {
	        request.setAttribute("headTitle", "Acceso denegado");
	        request.setAttribute("bodyTitle", "Debe iniciar sesión para crear un quiz");
	        request.setAttribute("buttonAction", "login.html");
	        request.setAttribute("buttonMessage", "Iniciar sesión");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
	    }
		
		request.setAttribute("room", foundRoom);
		request.getRequestDispatcher("WEB-INF/createquiz.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TopicLogic topicLogic = new TopicLogic();
		OptionLogic optionLogic = new OptionLogic();
		QuestionLogic questionLogic = new QuestionLogic();
		QuizLogic quizLogic = new QuizLogic();
		RoomLogic roomLogic = new RoomLogic();

		
		User usr = (User) request.getSession().getAttribute("user");
		
		Room room = new Room();
		room.setCode(request.getParameter("room"));
		Room foundRoom = roomLogic.getRoomByCode(room);
		
		Topic topic = new Topic();
		topic.setId(Integer.parseInt(request.getParameter("topicId")));
		Topic foundTopic = topicLogic.getById(topic);
		
		
		if(foundRoom == null) {
			request.setAttribute("headTitle", "Acceso denegado");
	        request.setAttribute("bodyTitle", "No se encuentra en una sala o la sesión expiró");
	        request.setAttribute("buttonAction", "index.jsp");
	        request.setAttribute("buttonMessage", "Aceptar");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
		}
		
		if (usr == null) {
	        request.setAttribute("headTitle", "Acceso denegado");
	        request.setAttribute("bodyTitle", "Debe iniciar sesión para crear un quiz");
	        request.setAttribute("buttonAction", "login.html");
	        request.setAttribute("buttonMessage", "Iniciar sesión");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
	    }
		
		Quiz quiz = new Quiz();
		quiz.setRoom(foundRoom);
		quiz.setTopic(foundTopic);
		quiz.setMaxDuration(Time.valueOf((request.getParameter("maxDuration"))+":00"));
		quiz.setName(request.getParameter("name"));
		
		Quiz savedQuiz = quizLogic.save(quiz);
		
		    for (int i = 0; i < 10; i++) {
		        String questionText = request.getParameter("question" + i);
		        if (questionText != null && !questionText.trim().isEmpty()) {
		        	
		        	int correctAnswer = Integer.parseInt(request.getParameter("correct-" + i));
		        	
		        	Question question = new Question();
		        	question.setQuestionText(questionText);
		            question.setCorrectAnswer(correctAnswer);
		            question.setQuiz(savedQuiz);
		            Question savedQuestion = questionLogic.save(question);
		            
		        	
		            String option1 = request.getParameter("option1-" + i);
		            Option firstOption = new Option();
		            firstOption.setOptionText(option1);
		            firstOption.setNumber(1);
		            firstOption.setQuestion(savedQuestion);
		            String option2 = request.getParameter("option2-" + i);
		            Option secondOption = new Option();
		            secondOption.setOptionText(option2);
		            secondOption.setNumber(2);
		            secondOption.setQuestion(savedQuestion);
		            String option3 = request.getParameter("option3-" + i);
		            Option thirdOption = new Option();
		            thirdOption.setOptionText(option3);
		            thirdOption.setNumber(3);
		            thirdOption.setQuestion(savedQuestion);
		            String option4 = request.getParameter("option4-" + i);
		            Option fourthOption = new Option();
		            fourthOption.setOptionText(option4);
		            fourthOption.setNumber(4);
		            fourthOption.setQuestion(savedQuestion);
		            
		            optionLogic.save(firstOption);
		            optionLogic.save(secondOption);
		            optionLogic.save(thirdOption);
		            optionLogic.save(fourthOption);
		            
		        }
		    }
        request.setAttribute("headTitle", "Quiz creado");
        request.setAttribute("bodyTitle", "Quiz creado con éxito");
        request.setAttribute("buttonAction", "index.jsp");
        request.setAttribute("buttonMessage", "Aceptar");
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        
	}
}


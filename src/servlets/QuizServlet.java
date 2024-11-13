package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Participation;
import entities.Question;
import entities.Quiz;
import entities.Response;
import entities.User;
import logic.EmailLogic;
import logic.ParticipationLogic;
import logic.QuizLogic;
import logic.RoomLogic;

/**
 * Servlet implementation class Quiz
 */
@WebServlet({ "/Quiz", "/quiz", "/QUIZ" })
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public QuizServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Integer id;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			id = -1;
		}
		if (id != -1) {
			User user = (User) request.getSession().getAttribute("user");
			// Verifica que el usuario haya iniciado sesión
			if (user != null) {
				// Busca Quiz con su Room con el id del Quiz
				QuizLogic quizLogic = new QuizLogic();
				Quiz foundQuiz = quizLogic.getById(new Quiz(id));
				
				// Verifica que haya encontrado el Quiz, que el mismo no este dado de baja y que su Room tampoco
				if (foundQuiz != null && !foundQuiz.isDeleted() && !foundQuiz.getRoom().isDeleted()) {
					
					// Verifica que el usuario tenga acceso a la Room
					RoomLogic roomLogic = new RoomLogic();
					if (roomLogic.confirmAccess(user, foundQuiz.getRoom()) != null) {
						
						// Verifica si el usuario ya contestó el Quiz y no tiene un intento del mismo vigente
						ParticipationLogic participationLogic = new ParticipationLogic();
						Participation ongoingParticipation = (Participation) request.getSession().getAttribute("attempt");
						Participation foundParticipation = participationLogic.getParticipationByUserAndQuiz(user, foundQuiz);
						
						if (ongoingParticipation != null && !ongoingParticipation.isValid()) {
							request.getSession().removeAttribute("attempt");
							ongoingParticipation = null;
						}
						
						if (foundParticipation == null && ongoingParticipation == null) {
							ongoingParticipation = new Participation(user, foundQuiz, 0, LocalDateTime.now() );
							participationLogic.saveParticipation(ongoingParticipation);
							request.getSession().setAttribute("attempt", ongoingParticipation);
							request.setAttribute("quiz", foundQuiz);
							request.getRequestDispatcher("WEB-INF/quiz.jsp").forward(request, response);
						} else {
							if (ongoingParticipation != null && ongoingParticipation.getQuiz().getId() == foundQuiz.getId()) {
								request.setAttribute("quiz", foundQuiz);
								request.getRequestDispatcher("WEB-INF/quiz.jsp").forward(request, response);
							} else if (ongoingParticipation != null) {
								request.setAttribute("headTitle", "Acceso denegado");
								request.setAttribute("bodyTitle", "Usted tiene un intento vigente de otro cuestionario");
								request.setAttribute("buttonAction", "quiz?id=" + ongoingParticipation.getQuiz().getId());
								request.setAttribute("buttonMessage", "Ir al cuestionario");
								request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
							} else {
								request.setAttribute("headTitle", "Acceso denegado");
								request.setAttribute("bodyTitle", "Usted ya intentó este cuestionario");
								request.setAttribute("buttonAction", "room?id=" + foundQuiz.getRoom().getId());
								request.setAttribute("buttonMessage", "Volver a la sala");
								request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
							}
						}
					} else {
						request.setAttribute("headTitle", "Acceso denegado");
						request.setAttribute("bodyTitle", "Usted no tiene acceso a la sala del formulario");
						request.setAttribute("buttonAction", "index.jsp");
						request.setAttribute("buttonMessage", "Aceptar");
						request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("headTitle", "Formulario no encontrado");
					request.setAttribute("bodyTitle", "El formulario no existe o fue eliminado o su sala fue eliminada");
					request.setAttribute("buttonAction", "index.jsp");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("headTitle", "Acceso denegado");
				request.setAttribute("bodyTitle", "Debe iniciar sesión para entrar a un formulario");
				request.setAttribute("buttonAction", "login.html");
				request.setAttribute("buttonMessage", "Iniciar sesión");
				request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
			}
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Participation ongoingParticipation = (Participation) request.getSession().getAttribute("attempt");
		
		if (ongoingParticipation != null && ongoingParticipation.isValid()) {
			
			ParticipationLogic participationLogic = new ParticipationLogic();
			
			// Construcción de ArrayList<Integer> de las respuestas
			ArrayList<Response> quizResponses = new ArrayList<Response>();
			Integer questionIndex = 0;
			
			for (Question question : ongoingParticipation.getQuiz().getQuestions()) {
				try {
					quizResponses.add(new Response(question, Integer.parseInt(request.getParameter(questionIndex.toString())) ));
				} catch (Exception e) {
					quizResponses.add(new Response(question, -1));
				}
				questionIndex++;
			}
			
			request.setAttribute("result", quizResponses);
			request.setAttribute("room", ongoingParticipation.getQuiz().getRoom().getId());
			
			// Cálculo respuestas correctas
			int amountRight = 0;
			for (Response quizResponse : quizResponses) {
				if (quizResponse.isCorrect()) {
					amountRight++;
				}
			}
			ongoingParticipation.setAmountRight(amountRight);
			participationLogic.updateParticipation(ongoingParticipation);
			
			if (ongoingParticipation.getUser() != null && ongoingParticipation.getUser().getEmail() != null) {
				EmailLogic mailLogic = new EmailLogic();
			    mailLogic.sendQuizResults(ongoingParticipation.getUser().getEmail(), quizResponses);
			}
			
			request.getSession().removeAttribute("attempt");
			ongoingParticipation = null;
			
			request.getRequestDispatcher("WEB-INF/quizresult.jsp").forward(request, response);

		} else {
			request.setAttribute("headTitle", "Respuesta no registrada");
			request.setAttribute("bodyTitle", "El intento expiró y no se registró su respuesta");
			request.setAttribute("buttonAction", "index.jsp");
			request.setAttribute("buttonMessage", "Aceptar");
			request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
		}
	}

}
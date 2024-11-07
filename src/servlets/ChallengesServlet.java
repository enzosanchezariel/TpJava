package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Challenge;
import entities.Topic;
import entities.User;
import logic.ChallengeLogic;
import logic.TopicLogic;

@WebServlet({ "/Challenges", "/challenges", "/CHALLENGES", "/challenge", "/Challenge", "/CHALLENGE" })
public class ChallengesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ChallengesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		ChallengeLogic challengeLogic = new ChallengeLogic();
		int id;
		if (user != null && user.isAdmin()) {
			if (request.getParameter("id") != null) {
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException e) {
					id = -1;
				}
				if (id != -1) {
					Challenge challenge = new Challenge();
					challenge.setIdChallenge(id);
					challenge = challengeLogic.getById(challenge);
					if (challenge != null) {
						request.setAttribute("challenge", challenge);
						request.getRequestDispatcher("WEB-INF/modifychallenge.jsp").forward(request, response);
					} else {
						request.setAttribute("headTitle", "Desafío no encontrado");
						request.setAttribute("bodyTitle", "Desafío no encontrado");
						request.setAttribute("buttonAction", "challenges");
						request.setAttribute("buttonMessage", "Aceptar");
						request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
					}
				}
			}
			request.setAttribute("challenges", challengeLogic.getAll());
			request.getRequestDispatcher("WEB-INF/listchallenges.jsp").forward(request, response);
			
		} else {
			request.setAttribute("headTitle", "Acceso denegado");
			request.setAttribute("bodyTitle", "Acceso denegado");
			request.setAttribute("buttonAction", "index.jsp");
			request.setAttribute("buttonMessage", "Aceptar");
			request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.isAdmin()) {
			if ("delete".equals(request.getParameter("action"))) {
				int id;
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException e) {
					id = -1;
				}
				if (id != -1) {
					ChallengeLogic challengeLogic = new ChallengeLogic();
					Challenge challenge = new Challenge();
					challenge.setIdChallenge(id);
					challengeLogic.delete(challenge);
					response.sendRedirect("challenges");
				}
			} else if ("modify".equals(request.getParameter("action"))) {
				int id;
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException e) {
					id = -1;
				}
				if (id != -1) {
					ChallengeLogic challengeLogic = new ChallengeLogic();
					
					int amountQuestions;
					try {
						amountQuestions = Integer.parseInt(request.getParameter("amountQuestions"));
					} catch (NumberFormatException e) {
						amountQuestions = -1;
					}
					
					int topicId;
					try {
						topicId = Integer.parseInt(request.getParameter("topic"));
					} catch (NumberFormatException e) {
						topicId = -1;
					}
					
					Challenge challenge = new Challenge(id, request.getParameter("name"), amountQuestions, new Topic(topicId));
					// Obtener topic de la DB.
					TopicLogic topicLogic = new TopicLogic();
					challenge.setTopic(topicLogic.getById(challenge.getTopic()));
					
					challenge = challengeLogic.validateAttributes(challenge);
					if (challenge != null) {
						challengeLogic.update(challenge);
						response.sendRedirect("challenges");
					} else {
						request.setAttribute("headTitle", "Modificación fallida");
						request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
						request.setAttribute("buttonAction", "challenges");
						request.setAttribute("buttonMessage", "Aceptar");
						request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("headTitle", "Modificación fallida");
					request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
					request.setAttribute("buttonAction", "challenges");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			} else if ("create".equals(request.getParameter("action"))) {
				ChallengeLogic challengeLogic = new ChallengeLogic();
				
				int amountQuestions;
				try {
					amountQuestions = Integer.parseInt(request.getParameter("amountQuestions"));
				} catch (NumberFormatException e) {
					amountQuestions = -1;
				}
				
				int topicId;
				try {
					topicId = Integer.parseInt(request.getParameter("topic"));
				} catch (NumberFormatException e) {
					topicId = -1;
				}
				
				Challenge challenge = new Challenge(0, request.getParameter("name"), amountQuestions, new Topic(topicId));
				
				TopicLogic topicLogic = new TopicLogic();
				challenge.setTopic(topicLogic.getById(challenge.getTopic()));
				
				challenge = challengeLogic.validateAttributes(challenge);
				if (challenge != null) {
					challengeLogic.save(challenge);
					response.sendRedirect("challenges");
				} else {
					request.setAttribute("headTitle", "Creación fallida");
					request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
					request.setAttribute("buttonAction", "challenges");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			}
		}
	}
}

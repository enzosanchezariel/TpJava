package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Topic;
import entities.User;
import logic.TopicLogic;

@WebServlet({ "/Challenges", "/challenges", "/CHALLENGES", "/challenge", "/Challenge", "/CHALLENGE" })
public class ChallengesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ChallengesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		TopicLogic topicLogic = new TopicLogic();
		int id;
		if (user != null && user.isAdmin()) {
			if (request.getParameter("id") != null) {
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException e) {
					id = -1;
				}
				if (id != -1) {
					Topic topic = new Topic(id);
					topic = topicLogic.getById(topic);
					if (topic != null && !topic.isDeleted()) {
						request.setAttribute("topic", topic);
						request.getRequestDispatcher("WEB-INF/modifytopic.jsp").forward(request, response);
					} else {
						request.setAttribute("headTitle", "Tema no encontrado");
						request.setAttribute("bodyTitle", "Tema no encontrado");
						request.setAttribute("buttonAction", "topics");
						request.setAttribute("buttonMessage", "Aceptar");
						request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
					}
				}
			}
			request.setAttribute("topics", topicLogic.getAll());
			request.getRequestDispatcher("WEB-INF/listtopics.jsp").forward(request, response);
			
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
					TopicLogic topicLogic = new TopicLogic();
					topicLogic.delete(new Topic(id));
					response.sendRedirect("topics");
				}
			} else if ("modify".equals(request.getParameter("action"))) {
				int id;
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException e) {
					id = -1;
				}
				if (id != -1) {
					TopicLogic topicLogic = new TopicLogic();
					Topic topic = new Topic(id, request.getParameter("name"));
					topic = topicLogic.validateAttributes(topic);
					if (topic != null) {
						topicLogic.update(topic);
						response.sendRedirect("topics");
					} else {
						request.setAttribute("headTitle", "Modificación fallida");
						request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
						request.setAttribute("buttonAction", "topics");
						request.setAttribute("buttonMessage", "Aceptar");
						request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("headTitle", "Modificación fallida");
					request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
					request.setAttribute("buttonAction", "topics");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			} else if ("create".equals(request.getParameter("action"))) {
				TopicLogic topicLogic = new TopicLogic();
				Topic topic = new Topic(0, request.getParameter("name"));
				topic = topicLogic.validateAttributes(topic);
				if (topic != null) {
					topicLogic.save(topic);
					response.sendRedirect("topics");
				} else {
					request.setAttribute("headTitle", "Creación fallida");
					request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
					request.setAttribute("buttonAction", "topics");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			}
		}
	}
}

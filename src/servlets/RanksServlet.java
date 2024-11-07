package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Rank;
import entities.Topic;
import entities.User;
import logic.RankLogic;
import logic.TopicLogic;

@WebServlet({ "/Ranks", "/RANKS", "/ranks", "/Rank", "/RANK", "/rank" })
public class RanksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RanksServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		RankLogic rankLogic = new RankLogic();
		int id;
		if (user != null && user.isAdmin()) {
			if (request.getParameter("id") != null) {
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException e) {
					id = -1;
				}
				if (id != -1) {
					Rank rank = new Rank();
					rank.setId(id);
					rank = rankLogic.getById(rank);
					if (rank != null) {
						request.setAttribute("rank", rank);
						request.getRequestDispatcher("WEB-INF/modifyrank.jsp").forward(request, response);
					} else {
						request.setAttribute("headTitle", "Rango no encontrado");
						request.setAttribute("bodyTitle", "Rango no encontrado");
						request.setAttribute("buttonAction", "ranks");
						request.setAttribute("buttonMessage", "Aceptar");
						request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
					}
				}
			}
			request.setAttribute("ranks", rankLogic.getAll());
			request.getRequestDispatcher("WEB-INF/listranks.jsp").forward(request, response);
			
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
					RankLogic rankLogic = new RankLogic();
					Rank rank = new Rank();
					rank.setId(id);
					rankLogic.delete(rank);
					response.sendRedirect("ranks");
				}
			} else if ("modify".equals(request.getParameter("action"))) {
				int id;
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException e) {
					id = -1;
				}
				if (id != -1) {
					RankLogic rankLogic = new RankLogic();
					
					int amountChallenges;
					try {
						amountChallenges = Integer.parseInt(request.getParameter("amountChallenges"));
					} catch (NumberFormatException e) {
						amountChallenges = -1;
					}
					
					Rank rank = new Rank(id, request.getParameter("name"), amountChallenges);
					rank = rankLogic.validateAttributes(rank);
					if (rank != null) {
						rankLogic.update(rank);
						response.sendRedirect("ranks");
					} else {
						request.setAttribute("headTitle", "Modificación fallida");
						request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
						request.setAttribute("buttonAction", "ranks");
						request.setAttribute("buttonMessage", "Aceptar");
						request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("headTitle", "Modificación fallida");
					request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
					request.setAttribute("buttonAction", "ranks");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			} else if ("create".equals(request.getParameter("action"))) {
				RankLogic rankLogic = new RankLogic();
				
				int amountChallenges;
				try {
					amountChallenges = Integer.parseInt(request.getParameter("amountChallenges"));
				} catch (NumberFormatException e) {
					amountChallenges = -1;
				}
				
				Rank rank = new Rank(0, request.getParameter("name"), amountChallenges);
				rank = rankLogic.validateAttributes(rank);
				if (rank != null) {
					rankLogic.save(rank);
					response.sendRedirect("ranks");
				} else {
					request.setAttribute("headTitle", "Creación fallida");
					request.setAttribute("bodyTitle", "Los campos tienen datos erroneos");
					request.setAttribute("buttonAction", "ranks");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			}
		}
	}
}
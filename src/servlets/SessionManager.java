package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import logic.Login;

@WebServlet({ "/Session", "/session", "/SESSION" })
public class SessionManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SessionManager() {
        super();
    }
    
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("login".equals(request.getParameter("action"))) {
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			
			User usr = new User(user, password);
			
			if (usr.getUser().equals("enzo") && usr.getPassword().equals("enzo")) {
				request.getSession().setAttribute("user", usr);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("headTitle", "Login fallido");
				request.setAttribute("bodyTitle", "El usuario ingresado no es válido");
				request.setAttribute("buttonAction", "login.html");
				request.setAttribute("buttonMessage", "Volver a intentar");
				request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
			}
		}
		else if ("logout".equals(request.getParameter("action"))) {
			request.getSession().invalidate();
			request.setAttribute("headTitle", "Sesión cerrada");
			request.setAttribute("bodyTitle", "Sesión cerrada");
			request.setAttribute("buttonAction", "index.jsp");
			request.setAttribute("buttonMessage", "Aceptar");
			request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
		}
	}
}

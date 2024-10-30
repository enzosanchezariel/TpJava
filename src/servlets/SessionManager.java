package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import logic.UserLogic;

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
			UserLogic userLogic = new UserLogic();
			
			String email = request.getParameter("user");
			String password = request.getParameter("password");
			User inputUsr = new User(email, password);
			
			User foundUsr = userLogic.searchAndCompare(inputUsr);
			
			if (foundUsr != null) {
				request.getSession().setAttribute("user", foundUsr);
				//request.getRequestDispatcher("index.jsp").forward(request, response);
				response.sendRedirect("index.jsp");
			} else {
				request.setAttribute("headTitle", "Login fallido");
				request.setAttribute("bodyTitle", "El usuario ingresado no es válido");
				request.setAttribute("buttonAction", "login.html");
				request.setAttribute("buttonMessage", "Volver a intentar");
				request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
			}
		}
		else if ("register".equals(request.getParameter("action"))){
			UserLogic userLogic = new UserLogic();
			
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			
			User inputUsr = new User(name, surname, email, password);
			
			User validatedUser = userLogic.validateRegisterAndReturnUser(inputUsr, confirmPassword);
			
			
			if( validatedUser != null) {
				request.getSession().setAttribute("user", validatedUser);
				response.sendRedirect("index.jsp");
			} else {
				request.setAttribute("headTitle", "Register fallido");
				request.setAttribute("bodyTitle", "El usuario ya existe o los campos de contraseña no son iguales");
				request.setAttribute("buttonAction", "signup.html");
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

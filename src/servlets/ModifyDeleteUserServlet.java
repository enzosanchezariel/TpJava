package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import logic.UserLogic;

/**
 * Servlet implementation class ModifyDeleteUserServlet
 */
@WebServlet({ "/account", "/ACCOUNT", "/Account" })
public class ModifyDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserLogic userLogic = new UserLogic();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String passwordConfirmation = (String) request.getAttribute("passwordConfirmation");
		
		if (user != null) {
			if (passwordConfirmation == null) {
			request.getRequestDispatcher("WEB-INF/confirmpassword.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("WEB-INF/modifyaccount.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		
		if ("modifyAccount".equals(request.getParameter("action"))) {
			if (user != null) {
				String name = request.getParameter("name");
				String surname = request.getParameter("surname");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String confirmPassword = request.getParameter("confirmPassword");
				
				User inputUsr = new User(user.getId(), name, surname, email, password);
				
				User validatedUser = userLogic.validateUpdateAndReturnUser(inputUsr, confirmPassword);
				
				if( validatedUser != null) {
					System.out.println("------- Modify account post -------");
					request.getSession().setAttribute("user", validatedUser);
					response.sendRedirect("index.jsp");
				} else {
					request.setAttribute("headTitle", "Modificación fallida");
					request.setAttribute("bodyTitle", "El email esta en uso por otro usuario o los campos tienen datos erroneos");
					request.setAttribute("buttonAction", "account");
					request.setAttribute("buttonMessage", "Volver a intentar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				}
			}
		}
		else if ("deleteAccount".equals(request.getParameter("action"))) {
			if (user != null) {
				System.out.println("------- Delete account post -------");
				userLogic.delete(user);
				request.getSession().invalidate();
				request.setAttribute("headTitle", "Cuenta eliminada");
				request.setAttribute("bodyTitle", "Cuenta eliminada");
				request.setAttribute("buttonAction", "index.jsp");
				request.setAttribute("buttonMessage", "Aceptar");
				request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
			}
		}
		else if ("confirmAccount".equals(request.getParameter("action"))) {
			String password = request.getParameter("password");
			User userToCheck = new User();
			userToCheck.setEmail(user.getEmail());
			userToCheck.setPassword(password);
			
			if (userLogic.searchAndCompare(userToCheck) != null) {
				request.setAttribute("passwordConfirmation", password);
				doGet(request, response);
			} else {
				request.setAttribute("headTitle", "Contraseña incorrecta");
				request.setAttribute("bodyTitle", "Contraseña incorrecta");
				request.setAttribute("buttonAction", "account");
				request.setAttribute("buttonMessage", "Reintentar");
				request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
			}
		}
	}

}

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

@WebServlet({ "/Signin", "/signin", "/SIGNIN", "/SignIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Signin() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		User usr = new User(user, password);
		
		if (usr.getUser().equals("enzo") && usr.getPassword().equals("enzo")) {
			request.getSession().setAttribute("user", usr);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/failedSignin.html").forward(request, response);
		}
	}

}

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Room;
import entities.User;
import logic.RoomLogic;


@WebServlet({ "/Room", "/room", "/ROOM" })
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RoomServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id;
		RoomLogic roomLogic = new RoomLogic();
		
		// Obtiene el id de la sala desde la URL e intenta convertirla a entero
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			id = -1;
		}
		if (id != -1) {
			User user = (User) request.getSession().getAttribute("user");
			Room room = new Room();
			room.setId(id);
			
			// Si el usuario no inició sesión o la misma expiró, pero entró al url
			// Muestra una pagina de error con un botón para iniciar sesión
			if (user == null) {
				request.setAttribute("headTitle", "Acceso denegado");
				request.setAttribute("bodyTitle", "Debe iniciar sesión para entrar a una sala");
				request.setAttribute("buttonAction", "login.html");
				request.setAttribute("buttonMessage", "Iniciar sesión");
				request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
			} else {
				Room foundRoom = roomLogic.confirmAccess(user, room);
				
				// Puede que roomLogic.confirmAccess() no encuentre la sala o el usuario no tenga acceso
				// Muestra una pagina de error con un botón para volver a inicio
				if (foundRoom == null) {
					request.setAttribute("headTitle", "Sala no encontrada");
					request.setAttribute("bodyTitle", "La sala no existe o usted no tiene acceso");
					request.setAttribute("buttonAction", "index.jsp");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				} else {
					request.setAttribute("room", foundRoom);
					request.getRequestDispatcher("WEB-INF/room.jsp").forward(request, response);
				}
			}
		}
	}

	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}*/

}
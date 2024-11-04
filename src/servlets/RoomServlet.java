package servlets;

import java.io.IOException;
import java.sql.Date;

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
				} else if (foundRoom.isDeleted()) {
					// Puede que si encuentre la sala, pero la misma esté dada de baja
					request.setAttribute("headTitle", "Sala eliminada");
					request.setAttribute("bodyTitle", "La sala a la que intenta ingresar fue eliminada");
					request.setAttribute("buttonAction", "index.jsp");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				} else if (!foundRoom.isValid()) {
					// Puede que la sala haya vencido
					request.setAttribute("headTitle", "Sala vencida");
					request.setAttribute("bodyTitle", "La sala a la que intenta ingresar ya venció");
					request.setAttribute("buttonAction", "index.jsp");
					request.setAttribute("buttonMessage", "Aceptar");
					request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
				} else {
					foundRoom.setAdmin(roomLogic.getRoomAdmin(foundRoom));
					foundRoom.setQuizzes(roomLogic.getRoomQuizzes(foundRoom));
					request.setAttribute("room", foundRoom);
					request.getRequestDispatcher("WEB-INF/room.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RoomLogic roomLogic = new RoomLogic();
	    User usr = (User) request.getSession().getAttribute("user");
    	String roomcode = (String) request.getParameter("code");
	    Room room = new Room(); 
	    room.setCode(roomcode);



	    if (usr == null) {
	        request.setAttribute("headTitle", "Acceso denegado");
	        request.setAttribute("bodyTitle", "Debe iniciar sesión para entrar a una sala");
	        request.setAttribute("buttonAction", "login.html");
	        request.setAttribute("buttonMessage", "Iniciar sesión");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
	    }

	    if (roomcode == "") {
	        response.sendRedirect("index.jsp");
	        return;
	    }

	    Room foundRoom = roomLogic.getRoomByCode(room);

	    if (foundRoom == null) {
	        request.setAttribute("headTitle", "Sala no encontrada");
	        request.setAttribute("bodyTitle", "La sala no existe");
	        request.setAttribute("buttonAction", "index.jsp");
	        request.setAttribute("buttonMessage", "Aceptar");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
	    }

	    if (foundRoom.isDeleted()) {
	        request.setAttribute("headTitle", "Sala eliminada");
	        request.setAttribute("bodyTitle", "La sala a la que intenta ingresar fue eliminada");
	        request.setAttribute("buttonAction", "index.jsp");
	        request.setAttribute("buttonMessage", "Aceptar");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
		}
	
		boolean possible = roomLogic.joinPossible(usr, foundRoom);
	
		if (!possible) {
		request.setAttribute("headTitle", "Sala encontrada");
		request.setAttribute("bodyTitle", "No es posible unirlo a la sala");
		request.setAttribute("buttonAction", "index.jsp");
		request.setAttribute("buttonMessage", "Aceptar");
		request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
		return;
		}
	
		roomLogic.addUserToRoom(usr, foundRoom);
		foundRoom.setAdmin(roomLogic.getRoomAdmin(foundRoom));
		foundRoom.setQuizzes(roomLogic.getRoomQuizzes(foundRoom));
	
		request.setAttribute("room", foundRoom);
		request.getRequestDispatcher("WEB-INF/room.jsp").forward(request, response);
	}
}

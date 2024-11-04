package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Room;
import entities.User;
import logic.RoomLogic;

/**
 * Servlet implementation class CreateRoomServlet
 */
@WebServlet({ "/CreateRoomServlet", "/createroom", "/CreateRoom", "/CREATEROOM", "/createRoom" })
public class CreateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usr = (User) request.getSession().getAttribute("user");
		if (usr == null) {
	        request.setAttribute("headTitle", "Acceso denegado");
	        request.setAttribute("bodyTitle", "Debe iniciar sesión para entrar a una sala");
	        request.setAttribute("buttonAction", "login.html");
	        request.setAttribute("buttonMessage", "Iniciar sesión");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
	    }
    	
    	request.getRequestDispatcher("WEB-INF/createroom.jsp").forward(request, response);	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usr = (User) request.getSession().getAttribute("user");
		RoomLogic roomLogic = new RoomLogic();
    	Room room = new Room(); 
		Integer maxAmountParticipants;
		String roomcode = request.getParameter("code");
    	String roomname = request.getParameter("name");
    	String endDateStr = request.getParameter("endDate");
    	LocalDate localDate = LocalDate.parse(endDateStr);
    	Date endDate = Date.valueOf(localDate);
    	room.setEndDate(endDate);
    	room.setName(roomname);
    	room.setCode(roomcode);
    	room.setAdmin(usr);
    	room.setInitDate(new Date(System.currentTimeMillis()));
    	try {
    		maxAmountParticipants = Integer.parseInt(request.getParameter("maxAmountParticipants"));
    		room.setMaxAmountParticipants(maxAmountParticipants);
    	} catch (NumberFormatException e) {
    		maxAmountParticipants = -1;
    	}
    	
    	if(maxAmountParticipants == -1 || maxAmountParticipants < 1) {
    		request.setAttribute("headTitle", "Caracteres invalidos");
	        request.setAttribute("bodyTitle", "Debe ingresar caracteres válidos para crear la sala");
	        request.setAttribute("buttonAction", "createRoom");
	        request.setAttribute("buttonMessage", "Volver a intentar");
	        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
	        return;
    	}
    	
    	Room existingRoom = roomLogic.checkExistingRooms(room);
    	 
    	if (existingRoom != null) {
    		request.setAttribute("room", existingRoom);
    		request.getRequestDispatcher("WEB-INF/joinexistingroom.jsp").forward(request, response);
    	}
    	
    	roomLogic.save(room);
    	Room createdRoom = roomLogic.getRoomByCode(room);
    	roomLogic.addUserToRoom(usr, createdRoom);
    	request.setAttribute("headTitle", "Sala creada");
        request.setAttribute("bodyTitle", "La sala ha sido creada con éxito");
        request.setAttribute("buttonAction", "index.jsp");
        request.setAttribute("buttonMessage", "Aceptar");
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
    	
	}

}

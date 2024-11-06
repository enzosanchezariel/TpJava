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

@WebServlet({ "/leavedeleteroom", "/LeaveDeleteRoom", "/LEAVEDELETEROOM" })
public class LeaveDeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LeaveDeleteRoomServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			} catch (NumberFormatException e) {
				id = -1;
			}
			if (id != -1) {
				Room room = new Room();
				System.out.println("------- 1 -------");
				room.setId(id);
				RoomLogic roomLogic = new RoomLogic();
				System.out.println(room.getId());
				room = roomLogic.getRoomById(room);
				if (room != null) {
					System.out.println("------- 2 -------");
					room.setAdmin(roomLogic.getRoomAdmin(room));
					if (room.getAdmin() != null && user.getId() == room.getAdmin().getId()) {
						System.out.println("------- Delete room post -------");
						roomLogic.delete(room);
						response.sendRedirect("index.jsp");
					} else {
						System.out.println("------- Leave room post -------");
						roomLogic.removeUserFromRoom(user, room);
						response.sendRedirect("index.jsp");
					}
				}
			}
		}
	}
}

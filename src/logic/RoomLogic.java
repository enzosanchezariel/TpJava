package logic;

import java.util.ArrayList;
import java.util.Date;

import data.UserRoomDB;
import entities.Room;
import entities.User;

public class RoomLogic {
	
	private UserRoomDB db = new UserRoomDB(); 
	
	public ArrayList<Room> getRoomsByUser(User user){
		// Devuelve las salas del usuario desde la base de datos.
		ArrayList<Room> roomsFromDb = db.getRoomsByUserId(user);
		//Return de prueba
		/*ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(1, "AMI UTN FRRO COM 101", 123123, 15, 20, new Date(), new Date(), false));
		rooms.add(new Room(2, "FISICA2", 123124, 8, 25, new Date(), new Date(), false));
		rooms.add(new Room(3, "ASDASDDASDADSD", 123125, 2, 10, new Date(), new Date(), false));
		rooms.add(new Room(4, "ASDDSASD SSDASDADS", 123126, 4, 5, new Date(), new Date(), false));
		rooms.add(new Room(5, "ASDASDSA", 123127, 20, 20, new Date(), new Date(), false));
		*/
		return roomsFromDb;
	}
	
	public Room confirmAccess(User user, Room room) {
		// TODO: Checkear que el usuario tenga acceso a la sala
		// TODO: En ese caso, devolver la sala. De lo contrario, null
		// TODO: Si la sala no existe, tambien devolver null
		ArrayList<Room> roomsFromDb = db.getRoomsByUserId(user);
		for (Room r: roomsFromDb) {
			if (r.getId() == room.getId()) {
				return r;
			}
		}
		return null;
		
		/* No pasamos enteros como parametro para id.
		 * Usamos la misma entidad con solo el id definido.
		 * Es una buena practica para evitar refactorear el codigo
		 * en caso de un cambio en como se identifica */
		
		// Return de prueba
		//return new Room(1, "AMI UTN FRRO COM 101", 123123, 15, 20, new Date(), new Date(), false);
	}
}
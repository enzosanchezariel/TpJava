package logic;

import java.util.ArrayList;
import java.util.Date;

import data.RoomDB;
import data.RoomQuizDB;
import data.UserRoomDB;
import entities.Quiz;
import entities.Room;
import entities.User;

public class RoomLogic {
	
	private UserRoomDB userRoomDB = new UserRoomDB();
	private RoomQuizDB roomQuizDB = new RoomQuizDB();
	private RoomDB roomDB = new RoomDB();
	
	public Room getRoomByCode(Room r) {
		Room room = roomDB.getByCode(r);
		if (room != null) {
			return room;
		} else {
			return null;
		}
	}
	
	public boolean joinPossible(User u, Room r) {
		ArrayList<Room> roomsFromDb = userRoomDB.getRoomsByUserId(u);
		for (Room aRoom: roomsFromDb) {
			if (aRoom.getId() == r.getId()) {
				return false;
			} 
		}
		if (r.getAmountParticipants() >= r.getMaxAmountParticipants()) {
			return false;
		}
		return true;
	}
	
	public void addUserToRoom(User u, Room r) {
		userRoomDB.addUserToRoom(u, r);;
	}
	
	public ArrayList<Room> getRoomsByUser(User user){
		ArrayList<Room> roomsFromDb = userRoomDB.getRoomsByUserId(user);
		return roomsFromDb;
	}
	
	public Room confirmAccess(User u, Room r) {
		ArrayList<Room> roomsFromDb = userRoomDB.getRoomsByUserId(u);
		for (Room aRoom: roomsFromDb) {
			if (aRoom.getId() == r.getId()) {
				aRoom.setAdmin(getRoomAdmin(aRoom));
				aRoom.setQuizzes(getRoomQuizzes(aRoom));
				return aRoom;
			}
		}
		return null;
	}
	
	public ArrayList<Quiz> getRoomQuizzes(Room r) {
		ArrayList<Quiz> quizzes = roomQuizDB.getQuizzesByRoomId(r);
		return quizzes;
	}

	public User getRoomAdmin(Room r) {
		User admin = userRoomDB.getRoomAdmin(r);
		return admin;
	}
}
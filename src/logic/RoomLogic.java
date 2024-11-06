package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.RoomDB;
import data.RoomQuizDB;
import data.UserRoomDB;
import entities.Quiz;
import entities.RankedUser;
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
	
	public Room getRoomById(Room r) {
		return roomDB.getById(r);
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
	
	public void removeUserFromRoom(User u, Room r) {
		userRoomDB.removeUserFromRoom(u, r);
	}
	
	public ArrayList<Room> getRoomsByUser(User user){
		ArrayList<Room> roomsFromDb = userRoomDB.getRoomsByUserId(user);
		return roomsFromDb;
	}
	
	public Room confirmAccess(User u, Room r) {
		ArrayList<Room> roomsFromDb = userRoomDB.getRoomsByUserId(u);
		for (Room aRoom: roomsFromDb) {
			if (aRoom.getId() == r.getId()) {
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
	
	public void setRoomAdmin(User u, Room r) {
		userRoomDB.setRoomAdmin(u, r);
	}
	
	public Room checkExistingRooms(Room r) {
		ArrayList<Room> roomsFromDb = roomDB.getAll();
		for (Room aRoom: roomsFromDb) {
			if(aRoom.getCode().equals(r.getCode())) {
				return aRoom;
			}
		}
		return null;
	}
	
	public void save(Room r) {
		roomDB.save(r);
	}
	
    public List<RankedUser> getRanking(Room room) {
        return RoomDB.getUsersbyRanking(room);
    }

	public void delete(Room r) {
		roomDB.delete(r);
	}
}
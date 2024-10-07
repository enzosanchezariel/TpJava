package data;

import entities.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
//import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.sql.Date;
import java.util.ArrayList;

import entities.Room;

public class UserRoomDB {
	
	public void addUserToRoom(User u, Room r, String role){
        String sqlSelect = "INSERT INTO users_rooms (user_id, room_id, role, joinedAt) VALUES (?, ?, ?, ?)";
        Connect connect = new Connect();
        Connection con = connect.getConnection();
        
        try {
        	PreparedStatement stm = con.prepareStatement(sqlSelect);
            stm.setInt(1, u.getId());
            stm.setInt(2, r.getId());
            stm.setString(3, role);
            stm.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stm.executeQuery();
            con.close();
        } catch(SQLException e){
        	e.printStackTrace();
        }
    }
	
	public ArrayList<Room> getRoomsByUserId(User u) {
		String sqlSelect = "select r.* from users_rooms ur inner join rooms r on ur.room_id = r.id where ru.id_user = ?";
        Connect connect = new Connect();
        Connection con = connect.getConnection();
        ArrayList<Room> roomsFromUser = new ArrayList<>();
        
        try {
        	PreparedStatement stm = con.prepareStatement(sqlSelect);
        	stm.setInt(1, u.getId());
        	ResultSet rs = stm.executeQuery();
        	while (rs.next()) {
        		int id = rs.getInt("id");
				String name = rs.getString("name");
				int code = rs.getInt("code");
				int amountParticipants = rs.getInt("amountParticipants");
				int maxAmountParticipants = rs.getInt("maxAmountParticipants");
				Date initDate = rs.getDate("initDate");
				Date endDate = rs.getDate("endDate");
				boolean deleted = rs.getBoolean("deleted");
				roomsFromUser.add(new Room(id, name, code, amountParticipants, maxAmountParticipants, initDate, endDate, deleted));
        	}
        	con.close();
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return roomsFromUser;
	}
}

// hacer getRoomsByUserId y getUsersByRoomId
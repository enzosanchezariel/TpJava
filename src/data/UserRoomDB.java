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
	
	public void addUserToRoom(User u, Room r){
        String sqlSelect = "INSERT INTO users_rooms (user_id, room_id) VALUES (?, ?)";
        Connect connect = new Connect();
        Connection con = connect.getConnection();
        
        if (con != null) {
        	try {
            	PreparedStatement stm = con.prepareStatement(sqlSelect);
                stm.setInt(1, u.getId());
                stm.setInt(2, r.getId());
                stm.executeUpdate();
                con.close();
            } catch(SQLException e){
            	e.printStackTrace();
            }
		}
    }
	
	public ArrayList<Room> getRoomsByUserId(User u) {
		String sqlSelect = "SELECT r.*, (SELECT COUNT(*) FROM users_rooms ur WHERE ur.room_id = r.id) AS amount_participants FROM rooms r INNER JOIN users_rooms ur ON ur.room_id = r.id WHERE ur.user_id = ?";
        Connect connect = new Connect();
        Connection con = connect.getConnection();
        ArrayList<Room> roomsFromUser = new ArrayList<>();
        
        if (con != null) {
        	try {
            	PreparedStatement stm = con.prepareStatement(sqlSelect);
            	stm.setInt(1, u.getId());
            	ResultSet rs = stm.executeQuery();
            	while (rs.next()) {
            		int id = rs.getInt("id");
    				String name = rs.getString("name");
    				int code = rs.getInt("code");
    				int amountParticipants = rs.getInt("amount_participants");
    				int maxAmountParticipants = rs.getInt("max_amount_participants");
    				Date initDate = rs.getDate("init_date");
    				Date endDate = rs.getDate("end_date");
    				boolean deleted = rs.getBoolean("deleted");
    				roomsFromUser.add(new Room(id, name, code, amountParticipants, maxAmountParticipants, initDate, endDate, deleted));
            	}
            	con.close();
            } catch(SQLException e) {
            	e.printStackTrace();
            }
		}
        
        return roomsFromUser;
	}
	
	public User getRoomAdmin(Room r) {
		String sqlSelect = "SELECT users.* FROM rooms INNER JOIN users ON users.id = rooms.admin WHERE rooms.id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		User user = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setInt(1, r.getId());
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					String email = rs.getString("email");
					String password = rs.getString("password");
					boolean deleted = rs.getBoolean("deleted");
					user = new User(id, name, surname, email, password, deleted);
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}
}
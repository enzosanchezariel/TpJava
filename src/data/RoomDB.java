package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.RankedUser;
import entities.Room;
import entities.User;

public class RoomDB {
	public ArrayList<Room> getAll(){
		String sqlSelect = "select r.*, (SELECT COUNT(*) FROM users_rooms ur WHERE ur.room_id = r.id) AS amount_participants from rooms r";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String code = rs.getString("code");
					int amountParticipants = rs.getInt("amount_participants");
					int maxAmountParticipants = rs.getInt("max_amount_participants");
					Date initDate = rs.getDate("init_date");
					Date endDate = rs.getDate("end_date");
					boolean deleted = rs.getBoolean("deleted");
					rooms.add(new Room(id, name, code, amountParticipants, maxAmountParticipants, initDate, endDate, deleted));
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rooms;
	}
	
	public Room getById(Room r) {
		String sqlSelect = "SELECT r.*, (SELECT COUNT(*) FROM users_rooms ur WHERE ur.room_id = r.id) AS amount_participants FROM rooms r WHERE r.id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		Room room = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setInt(1, r.getId());
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String code = rs.getString("code");
					int amountParticipants = rs.getInt("amount_participants");
					int maxAmountParticipants = rs.getInt("max_amount_participants");
					Date initDate = rs.getDate("init_date");
					Date endDate = rs.getDate("end_date");
					boolean deleted = rs.getBoolean("deleted");
					room = new Room(id, name, code, amountParticipants, maxAmountParticipants, initDate, endDate, deleted);
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return room;
	}
	
	
	public Room getByCode(Room r) {
		String sqlSelect = "select r.*, (SELECT COUNT(*) FROM users_rooms ur WHERE ur.room_id = r.id)  AS amount_participants from rooms r where r.code = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		Room room = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setString(1, r.getCode());
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String code = rs.getString("code");
					int amountParticipants = rs.getInt("amount_participants");
					int maxAmountParticipants = rs.getInt("max_amount_participants");
					Date initDate = rs.getDate("init_date");
					Date endDate = rs.getDate("end_date");
					boolean deleted = rs.getBoolean("deleted");
					room = new Room(id, name, code, amountParticipants, maxAmountParticipants, initDate, endDate, deleted);
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return room;
	}
	
	
	
	
	public void save(Room r) {
		String sqlSelect = "insert into rooms(name, code, max_amount_participants, init_date, end_date, admin) values(?, ?, ?, ?, ?, ?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getName());
				stm.setString(2, r.getCode());
				stm.setInt(3, r.getMaxAmountParticipants());
				stm.setDate(4, r.getInitDate());
				stm.setDate(5, r.getEndDate());
				stm.setInt(6, r.getAdmin().getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public void delete(Room r) {
		String sqlSelect = "update rooms set deleted = 1 where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, r.getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update(Room r) {
		String sqlSelect = "update rooms set name = ?, code = ?, amount_participants = ?, max_amount_participants = ?, init_date = ?, end_date = ? where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getName());
				stm.setString(2, r.getCode());
				stm.setInt(3, r.getAmountParticipants());
				stm.setInt(4, r.getMaxAmountParticipants());
				stm.setDate(5, r.getInitDate());			
				stm.setDate(6, r.getEndDate());	
				stm.setInt(7, r.getId());	
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static  ArrayList<RankedUser> getUsersbyRanking(Room r) {
	    ArrayList<RankedUser> rankedUsers = new ArrayList<>();
	    String sqlSelect = "SELECT u.id, u.name, u.surname, SUM(a.amountRight) AS totalPuntos "
	                     + "FROM users u "
	                     + "INNER JOIN answers a ON a.user_id = u.id "
	                     + "INNER JOIN quizzes q ON q.id = a.quizz_id "
	                     + "WHERE q.room_id = ? "
	                     + "GROUP BY u.id "
	                     + "ORDER BY totalPuntos DESC";
	    
	    Connect connect = new Connect();
	    Connection con = connect.getConnection();
	    
	    if (con != null) {
	        try {
	            PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setInt(1, r.getId()); 
	            ResultSet rs = stm.executeQuery();
	            
	            while (rs.next()) {
	                int userId = rs.getInt("id");
	                String name = rs.getString("name");
	                String surname = rs.getString("surname");
	                int totalScore = rs.getInt("totalPuntos");
	                User user = new User(userId, name, surname);
	                RankedUser rankedUser = new RankedUser(user, totalScore);
	                rankedUsers.add(rankedUser);
	            }
	            rs.close();
	            stm.close(); 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return rankedUsers;
	}
}

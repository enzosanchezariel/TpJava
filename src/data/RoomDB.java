package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import entities.Room;

public class RoomDB {
	public ArrayList<Room> getAll(){
		String sqlSelect = "select * from rooms";
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
					int code = rs.getInt("code");
					int amountParticipants = rs.getInt("amountParticipants");
					int maxAmountParticipants = rs.getInt("maxAmountParticipants");
					Date initDate = rs.getDate("initDate");
					Date endDate = rs.getDate("endDate");
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
		String sqlSelect = "select * from rooms where id = ?";
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
					int code = rs.getInt("code");
					int amountParticipants = rs.getInt("amountParticipants");
					int maxAmountParticipants = rs.getInt("maxAmountParticipants");
					Date initDate = rs.getDate("initDate");
					Date endDate = rs.getDate("endDate");
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
		String sqlSelect = "insert into rooms(name, code, amountParticipants, maxAmountParticipants, initDate, endDate) values(?, ?, ?, ?, ?, ?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getName());
				stm.setInt(2, r.getCode());
				stm.setInt(3, r.getAmountParticipants());
				stm.setInt(4, r.getMaxAmountParticipants());
				stm.setString(5, r.getInitDate());
				stm.setString(6, r.getEndDate());			
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
		String sqlSelect = "update rooms set name = ?, code = ?, amountParticipants = ?, maxAmountParticipants = ?, initDate = ?, endDate = ? where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getName());
				stm.setInt(2, r.getCode());
				stm.setInt(3, r.getAmountParticipants());
				stm.setInt(4, r.getMaxAmountParticipants());
				stm.setString(5, r.getInitDate());			
				stm.setString(6, r.getEndDate());	
				stm.setInt(7, r.getId());	
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

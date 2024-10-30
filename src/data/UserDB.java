package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.User;

public class UserDB {
	
	public ArrayList<User> getAll(){
		String sqlSelect = "select * from users";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		ArrayList<User> users = new ArrayList<User>();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					String email = rs.getString("email");
					String password = rs.getString("password");
					boolean deleted = rs.getBoolean("deleted");
					users.add(new User(id, name, surname, email, password, deleted));
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return users;
	}
	
	public User getById(User u) {
		String sqlSelect = "select * from users where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		User user = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setInt(1, u.getId());
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
	
	public User getByEmail(User u) {
		String sqlSelect = "select * from users where email = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		User user = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setString(1, u.getEmail());
				ResultSet rs = stm.executeQuery();
				
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					String email = rs.getString("email");
					String password = rs.getString("password");
					boolean deleted = rs.getBoolean("deleted");
					//System.out.println(deleted);
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
	
	public void save(User u) {
		String sqlSelect = "insert into users(name, surname, email, password, deleted) values(?, ?, ?, ?, ?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, u.getName());
				stm.setString(2, u.getSurname());
				stm.setString(3, u.getEmail());
				stm.setString(4, u.getPassword());
				stm.setBoolean(5, u.isDeleted());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void delete(User u) {
		String sqlSelect = "update users set deleted = 1 where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, u.getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update(User u) {
		String sqlSelect = "update users set name = ?, surname = ?, email = ?, password = ? where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, u.getName());
				stm.setString(2, u.getSurname());
				stm.setString(3, u.getEmail());
				stm.setString(4, u.getPassword());
				stm.setInt(5, u.getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Topic;

public class TopicDB {

		public ArrayList<Topic> getAll(){
			String sqlSelect = "select * from topics";
			Connect connect = new Connect();
			Connection con = connect.getConnection();
			ArrayList<Topic> topics = new ArrayList<Topic>();
			
			if (con != null) {
				try {
					PreparedStatement stm = con.prepareStatement(sqlSelect);
					ResultSet rs = stm.executeQuery();
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						boolean deleted = rs.getBoolean("deleted");
						topics.add(new Topic(id, name, deleted));
					}
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return topics;
		}
		
		public Topic getByName(Topic t) {
			String sqlSelect = "select * from topics where name = ?";
			Connect connect = new Connect();
			Connection con = connect.getConnection();
			Topic topic = null;
			
			if (con != null) {
				try {
					PreparedStatement stm = con.prepareStatement(sqlSelect);
		            stm.setString(1, t.getName());
					ResultSet rs = stm.executeQuery();
					if (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						boolean deleted = rs.getBoolean("deleted");
						topic = new Topic(id, name, deleted);
					}
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return topic;
		}
	}
	


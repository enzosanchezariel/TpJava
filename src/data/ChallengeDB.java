package data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Challenge;
import entities.Topic;


public class ChallengeDB {
	
	public ArrayList<Challenge> getAll(){
		String sqlSelect = "select * from challenges";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		ArrayList<Challenge> challenges = new ArrayList<Challenge>();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int idChallenge = rs.getInt("id");
					String nameChallenge = rs.getString("name");
					int amountQuestions = rs.getInt("amount_questions");
					int topicId = rs.getInt("topic");
					Topic topic = new Topic(topicId);
					
					challenges.add(new Challenge(idChallenge,nameChallenge,amountQuestions,topic));
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return challenges;	
	}
	
	
	public Challenge getById(Challenge c) {
		String sqlSelect = "select * from challenges where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		Challenge challenge = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setInt(1, c.getIdChallenge());
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int idChallenge = rs.getInt("id");
					String nameChallenge = rs.getString("name");
					int amountQuestions = rs.getInt("amount_questions");
					int topicId = rs.getInt("topic");
					Topic topic = new Topic(topicId);	
					challenge = new Challenge(idChallenge,nameChallenge,amountQuestions,topic);
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return challenge;
		
	}
	
	public void save(Challenge c) {
		String sqlSelect = "insert into challenges(name,amount_questions,topic) value (?,?,?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, c.getNameChallenge());
				stm.setInt(2, c.getAmountQuestions());
				stm.setInt(3, c.getTopic().getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Challenge c) {
		String sqlSelect = "delete from challenges where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, c.getIdChallenge());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void update(Challenge c) {
		String sqlSelect = "update challenges set name = ?, amount_questions = ?, topic = ? where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if(con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, c.getNameChallenge());
				stm.setInt(2, c.getAmountQuestions());
				stm.setInt(3, c.getTopic().getId());
				stm.setInt(4, c.getIdChallenge());
				stm.executeUpdate();
				con.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	
	
}

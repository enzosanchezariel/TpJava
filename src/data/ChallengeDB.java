package data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Challenge;
import entities.Topic;
import entities.User;


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

	public ArrayList<Challenge> challengesByUserId(User u){
		String sqlSelect = "SELECT challenges.* FROM challenges\r\n"
				+ "INNER JOIN topics\r\n"
				+ "ON challenges.topic = topics.id\r\n"
				+ "INNER JOIN (\r\n"
				+ "	SELECT topics.id, sum(amountRight) AS sumAmountRight FROM answers\r\n"
				+ "	INNER JOIN quizzes ON answers.quizz_id = quizzes.id\r\n"
				+ "    INNER JOIN topics ON quizzes.topic_id = topics.id\r\n"
				+ "	WHERE answers.user_id = ?\r\n"
				+ "	GROUP BY topics.id\r\n"
				+ ") AS res\r\n"
				+ "ON res.id = challenges.topic and res.sumAmountRight >= challenges.amount_questions\r\n"
				+ "ORDER BY challenges.topic";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		ArrayList<Challenge> challenges = new ArrayList<Challenge>();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, u.getId());
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
	
}

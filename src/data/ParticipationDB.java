package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Participation;
import entities.Quiz;
import entities.User;

public class ParticipationDB {
	
	public Participation getParticipationByUserAndQuiz(User u, Quiz q) {
		Participation participation = null;
		String sqlSelect = "SELECT * FROM answers WHERE user_id = ? AND quizz_id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
	        try {
	        	PreparedStatement stmt = con.prepareStatement(sqlSelect);
	            stmt.setInt(1, u.getId()); 
	            stmt.setInt(2, q.getId()); 
	
	            ResultSet rs = stmt.executeQuery();
	
	            if (rs.next()) {
	                participation = new Participation();
	                participation.setUser(u); 
	                participation.setQuiz(q); 
	                participation.setAmountRight(rs.getInt("amountRight"));
	
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}

        return participation;
		
		
	}

	public void saveParticipation(Participation p) {
		// TODO: Leo
	}
}
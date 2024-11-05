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
		String sqlSelect = "insert into answers(user_id, quizz_id, amountRight) values(?, ?, ?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, p.getUser().getId());
				stm.setInt(2, p.getQuiz().getId());
				stm.setInt(3, p.getAmountRight());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(Participation p) {
		String sqlSelect = "UPDATE answers SET amountRight = ? WHERE (user_id = ?) and (quizz_id = ?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, p.getAmountRight());
				stm.setInt(2, p.getUser().getId());
				stm.setInt(3, p.getQuiz().getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
package data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Rank;
import entities.User;

public class RankDB {
	
	public ArrayList<Rank> getAll(){
		String sqlSelect = "select * from ranks";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		ArrayList<Rank> ranks = new ArrayList<Rank>();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int idRange = rs.getInt("id");
					String description = rs.getString("name");
					int amountChallenges = rs.getInt("amount_challenges");
					ranks.add(new Rank(idRange, description, amountChallenges));
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ranks;
		
	}
	
	
	public Rank getById(Rank r) {
		String sqlSelect = "select * from ranks where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		Rank rank = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
	            stm.setInt(1, r.getId());
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int idRange = rs.getInt("id");
					String description = rs.getString("name");
					int amountChallenges = rs.getInt("amount_challenges");
					rank = new Rank(idRange, description, amountChallenges);
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rank;
	}
	
	
	public void save(Rank r) {
		String sqlSelect = "insert into ranks(name,amount_challenges) value (?,?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if(con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getName());
				stm.setInt(2, r.getAmountChallenges());
				stm.executeUpdate();
				con.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Rank r) {
		
		String sqlSelect = "delete from ranks where id = ?";
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
	
	
	public void update(Rank r) {
		
		String sqlSelect = "update ranks set name = ?, amount_challenges = ? where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		if(con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getName());
				stm.setInt(2, r.getAmountChallenges());
				stm.setInt(3, r.getId());
				stm.executeUpdate();
				con.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

	public Rank getRankByUserId(User u){
		String sqlSelect = "SELECT * FROM ranks\r\n"
				+ "WHERE ranks.amount_challenges = (\r\n"
				+ "	SELECT max(amount_challenges) FROM ranks\r\n"
				+ "	WHERE amount_challenges <= (\r\n"
				+ "		SELECT count(*) as countAmountChallenges FROM challenges\r\n"
				+ "		INNER JOIN topics\r\n"
				+ "		ON challenges.topic = topics.id\r\n"
				+ "		INNER JOIN (\r\n"
				+ "			SELECT topics.id, sum(amountRight) AS sumAmountRight FROM answers\r\n"
				+ "			INNER JOIN quizzes ON answers.quizz_id = quizzes.id\r\n"
				+ "		    INNER JOIN topics ON quizzes.topic_id = topics.id\r\n"
				+ "			WHERE answers.user_id = ?\r\n"
				+ "			GROUP BY topics.id\r\n"
				+ "		) AS res\r\n"
				+ "		ON res.id = challenges.topic and res.sumAmountRight >= challenges.amount_questions\r\n"
				+ "		ORDER BY challenges.topic\r\n"
				+ "	)\r\n"
				+ ")";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		Rank rank = new Rank();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				int idRange = rs.getInt("id");
				String description = rs.getString("name");
				int amountChallenges = rs.getInt("amount_challenges");
				rank = new Rank(idRange, description, amountChallenges);
			
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rank;
		
	}
	
	
	
}

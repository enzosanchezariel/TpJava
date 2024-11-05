package data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Rank;

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
					String description = rs.getString("description");
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
	            stm.setInt(1, r.getIdRange());
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int idRange = rs.getInt("id");
					String description = rs.getString("description");
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
		String sqlSelect = "insert into ranks(description,amount_challenges) value (?,?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if(con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getDescription());
				stm.setInt(2, r.getAmountChallenges());
				stm.executeUpdate();
				con.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void detele(Rank r) {
		
		String sqlSelect = "delete * from ranks where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, r.getIdRange());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void update(Rank r) {
		
		String sqlSelect = "update ranks set description = ?, amount_challenges = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		if(con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setString(1, r.getDescription());
				stm.setInt(2, r.getAmountChallenges());
				stm.executeUpdate();
				con.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

	
}

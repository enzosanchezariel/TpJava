package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Option;

public class OptionDB {

	public void save(Option o) {
		String sqlSelect = "insert into options(number, option_text, question_id) values(?, ?, ?)";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, o.getNumber());
				stm.setString(2, o.getOptionText());
				stm.setInt(3, o.getQuestion().getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

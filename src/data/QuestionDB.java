package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Question;

public class QuestionDB {
	
	public Question save(Question q) {
	    String sqlInsert = "INSERT INTO questions(question_text, correct_answer, quiz_id) VALUES(?, ?, ?)";
	    Connect connect = new Connect();
	    Connection con = connect.getConnection();
	    Question savedQuestion = new Question();
	    if (con != null) {
	        try {
	            PreparedStatement stm = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
	            stm.setString(1, q.getQuestionText());
	            stm.setInt(2, q.getCorrectAnswer());
	            stm.setInt(3, q.getQuiz().getId());
	            stm.executeUpdate();
	            ResultSet rs = stm.getGeneratedKeys();
	            if (rs.next()) {
	                int generatedId = rs.getInt(1);
	                savedQuestion.setId(generatedId);
	                savedQuestion.setQuestionText(q.getQuestionText());
	                savedQuestion.setCorrectAnswer(q.getCorrectAnswer());
	                savedQuestion.setQuiz(q.getQuiz());
	            }
	            
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return savedQuestion;
	}
	
}

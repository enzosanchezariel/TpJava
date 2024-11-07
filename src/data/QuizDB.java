package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import entities.Question;
import entities.Quiz;
import entities.Room;
import entities.Topic;

public class QuizDB {

	public Quiz getById(Quiz q) {
		String sqlSelect = "SELECT * FROM quizzes WHERE id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		Quiz quiz = null;
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, q.getId());
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					
					
					int id = rs.getInt("id");
            		String name = rs.getString("name");
            		Time maxDuration = rs.getTime("max_duration");
            		boolean deleted = rs.getBoolean("deleted");
            		
            		Topic topic = new Topic(rs.getInt("topic_id"));
            		
            		Room room = new Room();
					room.setId(rs.getInt("room_id"));
					
					ArrayList<Question> questions = this.getQuestionsByQuizId(q);
            		
            		
					quiz = new Quiz(id, name, maxDuration, room, topic, deleted, questions);
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return quiz;
	}
	
	public ArrayList<Question> getQuestionsByQuizId(Quiz q) {
		String sqlSelect = "SELECT * FROM questions WHERE quiz_id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		ArrayList<Question> questions = new ArrayList<Question>();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, q.getId());
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String questionText = rs.getString("question_text");
					int correctAnswer = rs.getInt("correct_answer");
					
					ArrayList<String> options = this.getOptionsByQuestionId(id);
					
					questions.add(new Question(id, questionText, correctAnswer, options));
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return questions;
	}

	private ArrayList<String> getOptionsByQuestionId(int question_id) {
		String sqlSelect = "SELECT * FROM options WHERE question_id = ? ORDER BY number";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		ArrayList<String> options = new ArrayList<String>();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, question_id);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					options.add(rs.getString("option_text"));
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return options;
	}
	
	
	public Quiz save(Quiz q) {
	    String sqlInsert = "INSERT INTO quizzes(name, max_duration, room_id, topic_id) VALUES(?, ?, ?, ?)";
	    Connect connect = new Connect();
	    Connection con = connect.getConnection();
	    Quiz savedQuiz = new Quiz();
	    if (con != null) {
	        try {
	            PreparedStatement stm = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
	            stm.setString(1, q.getName());
	            stm.setTime(2, q.getMaxDuration());
	            stm.setInt(3, q.getRoom().getId());
	            stm.setInt(4, q.getTopic().getId());
	            stm.executeUpdate();
	            ResultSet rs = stm.getGeneratedKeys();
	            if (rs.next()) {
	                int generatedId = rs.getInt(1);
	                savedQuiz.setId(generatedId);
	                savedQuiz.setName(q.getName());
	                savedQuiz.setMaxDuration(q.getMaxDuration());
	                savedQuiz.setRoom(q.getRoom());
	                savedQuiz.setTopic(q.getTopic());
	            }
	            
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return savedQuiz;
	}

	public void delete(Quiz q) {
		String sqlSelect = "update quizzes set deleted = 1 where id = ?";
		Connect connect = new Connect();
		Connection con = connect.getConnection();
		
		if (con != null) {
			try {
				PreparedStatement stm = con.prepareStatement(sqlSelect);
				stm.setInt(1, q.getId());
				stm.executeUpdate();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
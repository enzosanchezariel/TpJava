package data;

import entities.Room;
import entities.Topic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import entities.Question;
import entities.Quiz;

public class RoomQuizDB {

	public ArrayList<Quiz> getQuizzesByRoomId(Room r){
		String sqlSelect = "select quizzes.id, quizzes.name, quizzes.max_duration, quizzes.topic_id, topics.name topic_name, quizzes.deleted from rooms inner join quizzes on quizzes.room_id = rooms.id inner join topics on topics.id = quizzes.topic_id where rooms.id = ?";
        Connect connect = new Connect();
        Connection con = connect.getConnection();
        ArrayList<Quiz> quizzesFromRoom = new ArrayList<Quiz>();
        
        if (con != null) {
        	try {
            	PreparedStatement stm = con.prepareStatement(sqlSelect);
            	stm.setInt(1, r.getId());
            	ResultSet rs = stm.executeQuery();
            	while (rs.next()) {
            		int id = rs.getInt("id");
            		String name = rs.getString("name");
            		Time maxDuration = rs.getTime("max_duration");
            		int topicId = rs.getInt("topic_id");
            		String topicName = rs.getString("topic_name");
            		boolean deleted = rs.getBoolean("deleted");
    				quizzesFromRoom.add(new Quiz(id, name, maxDuration, new Topic(topicId, topicName), deleted));
            	}
            	con.close();
            } catch(SQLException e) {
            	e.printStackTrace();
            }
		}
        
        return quizzesFromRoom;
	}
}
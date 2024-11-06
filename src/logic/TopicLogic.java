package logic;

import java.util.ArrayList;

import data.TopicDB;
import entities.Topic;

public class TopicLogic {
	
	TopicDB topicDB = new TopicDB();
	
	public ArrayList<Topic> getAll() {
		ArrayList<Topic> topics =topicDB.getAll();
		return topics;
	}
	
	public Topic getByName(Topic t) {
		Topic topic = topicDB.getByName(t);
		return topic;
	}
	
}

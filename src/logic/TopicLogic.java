package logic;

import java.util.ArrayList;

import data.TopicDB;
import entities.Topic;

import com.mysql.cj.util.StringUtils;

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

	public Topic getById(Topic t) {
		Topic topic = new Topic(t.getId(), "Banana", false);
		return topic;
	}

	
	public Topic validateAttributes(Topic t) {
		if (t.getId() >= 0 && t.getName() != null && !StringUtils.isEmptyOrWhitespaceOnly(t.getName())) {
			return(t);
		}
		return null;
	}


	public void save(Topic t) {
		topicDB.save(t);
	}
	
	public void delete(Topic t) {
		topicDB.delete(t);
	}
	
	public void update(Topic t) {
		topicDB.update(t);
	}

}
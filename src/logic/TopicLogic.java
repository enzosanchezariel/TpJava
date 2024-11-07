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
	
	public ArrayList<Topic> getAllNotDisabled() {
		ArrayList<Topic> topics = topicDB.getAll();
		ArrayList<Topic> filteredTopics = new ArrayList<Topic>();
		for (Topic topic : topics) {
			if (!topic.isDeleted()) {
				filteredTopics.add(topic);
			}
		}
		return filteredTopics;
	}
	
	
	public Topic getById(Topic t) {
		Topic topic = topicDB.getById(t);
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
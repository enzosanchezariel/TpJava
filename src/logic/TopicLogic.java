package logic;

import java.util.ArrayList;

import com.mysql.cj.util.StringUtils;

import entities.Topic;

public class TopicLogic {
	//TopicDB topicDB = new TopicDB();
	
	public ArrayList<Topic> getAll() {
		ArrayList<Topic> topics = new ArrayList<Topic>();
		topics.add(new Topic(1, "Banana", false));
		return topics;
	}

	public Topic getById(Topic t) {
		Topic topic = new Topic(t.getId(), "Banana", false);
		return topic;
	}

	public void delete(Topic t) {
	}

	public Topic validateAttributes(Topic t) {
		if (t.getId() >= 0 && t.getName() != null && !StringUtils.isEmptyOrWhitespaceOnly(t.getName())) {
			return(t);
		}
		return null;
	}

	public void save(Topic t) {
	}
	
	public void update(Topic t) {
	}
}
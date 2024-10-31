package entities;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Quiz {
	int id;
	String name;
	Time maxDuration;
	Topic topic;
	ArrayList<Question> questions;
	boolean deleted = false;
	
	SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getMaxDuration() {
		return maxDuration;
	}
	
	public String getMaxDurationAsString() {
		if (maxDuration != null) {
			return outputFormat.format(maxDuration);
		}
		return "";
	}

	public void setMaxDuration(Time maxDuration) {
		this.maxDuration = maxDuration;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public Quiz() {}

	public Quiz(int id, String name, Time maxDuration, Topic topic, boolean deleted, ArrayList<Question> questions) {
		super();
		this.id = id;
		this.name = name;
		this.maxDuration = maxDuration;
		this.questions = questions;
		this.topic = topic;
		this.deleted = deleted;
	}
	
	public Quiz(int id, String name, Time maxDuration, Topic topic, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.maxDuration = maxDuration;
		this.topic = topic;
		this.deleted = deleted;
	}
}

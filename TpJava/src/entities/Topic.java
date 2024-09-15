package entities;

public class Topic {
	int idTopic;
	String description;
	
	public int getIdTopic() {
		return idTopic;
	}
	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Topic() {}
	
	public Topic(int idTopic, String description) {
		this.idTopic = idTopic;
		this.description = description;
	}
	
}

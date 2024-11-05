package entities;

public class Topic {
	int id;
	String name;
	boolean deleted;
	
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

	public boolean isDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public Topic(int topicId) {
		this.id = id;
	}
	
	public Topic(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Topic(int id, String name, boolean deleted) {
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}
	
}

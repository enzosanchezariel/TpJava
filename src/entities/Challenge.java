package entities;
import entities.Topic;

public class Challenge {
	private int idChallenge;
	private String nameChallenge;
	private int amountQuestions;
	private Topic topic;
	
	public int getIdChallenge() {
		return idChallenge;
	}
	public void setIdChallenge(int idChallenge) {
		this.idChallenge = idChallenge;
	}
	public String getNameChallenge() {
		return nameChallenge;
	}
	public void setNameChallenge(String nameChallenge) {
		this.nameChallenge = nameChallenge;
	}
	public int getAmountQuestions() {
		return amountQuestions;
	}
	public void setAmountQuestions(int amountQuestions) {
		this.amountQuestions = amountQuestions;
	}

	
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public Challenge (int idChallenge, String nameChallenge, int amountQuestions, Topic topic) {
		this.idChallenge = idChallenge;
		this.nameChallenge = nameChallenge;
		this.amountQuestions = amountQuestions;
		this.topic = topic; 
	}
	
}

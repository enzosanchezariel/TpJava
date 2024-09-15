package entities;

public class Questionary {
	int idQuestionary;
	String title;
	String description;
	int durationMax;
	
	public int getIdQuestionary() {
		return idQuestionary;
	}
	public void setIdQuestionary(int idQuestionary) {
		this.idQuestionary = idQuestionary;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDurationMax() {
		return durationMax;
	}
	public void setDurationMax(int durationMax) {
		this.durationMax = durationMax;
	}

	public Questionary() {}
	
	public Questionary(int idQuestionary, String title, String description, int durationMax ) {
		this.idQuestionary = idQuestionary;
		this.title = title;
		this.description = description;
		this.durationMax = durationMax;
	}
	
}

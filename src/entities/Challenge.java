package entities;

public class Challenge {
	int idChallenge;
	String nameChallenge;
	int amountQuestions;
	
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
	
	
	public Challenge() {}
	
	public Challenge (int idChallenge, String nameChallenge, int amountQuestions) {
		this.idChallenge = idChallenge;
		this.nameChallenge = nameChallenge;
		this.amountQuestions = amountQuestions;
	}
	
}

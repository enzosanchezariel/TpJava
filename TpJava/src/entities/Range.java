package entities;

public class Range {
	int idRange;
	String description;
	int amountChallenges;
	
	public int getIdRange() {
		return idRange;
	}
	public void setIdRange(int idRange) {
		this.idRange = idRange;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmountChallenges() {
		return amountChallenges;
	}
	public void setAmountChallenges(int amountChallenges) {
		this.amountChallenges = amountChallenges;
	}
	
	public Range() {}
	
	public Range(int idRange, String description, int amountChallenge) {
		this.idRange = idRange;
		this.description = description;
		this.amountChallenges = amountChallenges;
	}
	
}

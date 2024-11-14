package entities;

public class Rank {
	int id;
	String name;
	int amountChallenges;
	
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
	public int getAmountChallenges() {
		return amountChallenges;
	}
	public void setAmountChallenges(int amountChallenges) {
		this.amountChallenges = amountChallenges;
	}
	
	public Rank() {}
	
	public Rank(int id, String name, int amountChallenges) {
		this.id = id;
		this.name = name;
		this.amountChallenges = amountChallenges;
	}
	

}

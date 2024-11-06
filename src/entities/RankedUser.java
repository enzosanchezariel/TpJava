package entities;

public class RankedUser {
	private User user;
	private int totalPoints;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public RankedUser(User user, int totalPoints) {
		super();
		this.user = user;
		this.totalPoints = totalPoints;
	}
	
	

}

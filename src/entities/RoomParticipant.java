package entities;

public class RoomParticipant {
	int scoreTotal;

	public int getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(int scoreTotal) {
		this.scoreTotal = scoreTotal;
	}
	
	public RoomParticipant() {}
	
	public RoomParticipant(int scoreTotal) {
		this.scoreTotal = scoreTotal;
	}
}

package entities;

import java.sql.Date;

public class Participation {
	Date startTime;
	Date endTime;
	int scoreUser;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getScoreUser() {
		return scoreUser;
	}
	public void setScoreUser(int scoreUser) {
		this.scoreUser = scoreUser;
	}
	
	public Participation() {}
	
	public Participation(Date startTime, Date endTime, int scoreUser ) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.scoreUser = scoreUser;
	}
}

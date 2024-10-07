package entities;

//import java.sql.Date;
//import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class UserRoom {
    private int userId;
    private int roomId;
    private int roomScore = 0;
	private String role;
    private Timestamp joinedAt; 
    

    public UserRoom(int userId, int roomId, int roomScore, String role, Timestamp joinedAt) {
        this.userId = userId;
        this.roomId = roomId;
        this.roomScore = roomScore;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    
    public int getRoomScore() {
		return roomScore;
	}

	public void setRoomScore(int roomScore) {
		this.roomScore = roomScore;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Timestamp joinedAt) {
		this.joinedAt = joinedAt;
	}


}
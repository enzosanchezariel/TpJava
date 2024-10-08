package entities;

import entities.User;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {
	private int id;
	private String name;
	private int code;
	private int amountParticipants;
	private int maxAmountParticipants;
	private Date initDate;
	private Date endDate;
	private boolean deleted = false;
	private ArrayList<User> users; 
	
	SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getAmountParticipants() {
		return amountParticipants;
	}

	public void setAmountParticipants(int amountParticipants) {
		this.amountParticipants = amountParticipants;
	}

	public int getMaxAmountParticipants() {
		return maxAmountParticipants;
	}

	public void setMaxAmountParticipants(int maxAmountParticipants) {
		this.maxAmountParticipants = maxAmountParticipants;
	}

	public String getInitDate() {
		return outputFormat.format(initDate);
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getEndDate() {
		return outputFormat.format(endDate);
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void addUser(User u) {
		this.users.add(u);
		this.amountParticipants=+1;
	}

	public Room() {}

	public Room(int id, String name, int code, int amountParticipants, int maxAmountParticipants, Date initDate, Date endDate, boolean deleted) {
		super();
		this.name = name;
		this.id = id;
		this.code = code;
		this.amountParticipants = amountParticipants;
		this.maxAmountParticipants = maxAmountParticipants;
		this.initDate = initDate;
		this.endDate = endDate;
		this.deleted = deleted;
	}
	
}
package entities;

import entities.User;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.sql.Date;

public class Room {
	private int id;
	private String name;
	private String code;
	private int amountParticipants;
	private int maxAmountParticipants;
	private Date initDate;
	private Date endDate;
	private boolean deleted = false;
	private User admin;
	private ArrayList<User> users;
	private ArrayList<Quiz> quizzes;
	
	SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public ArrayList<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(ArrayList<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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

	public Date getInitDate() {
		return initDate;
	}
	
	public String getInitDateAsString() {
		if (initDate != null) {
			return outputFormat.format(initDate);
		}
		return "";
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public String getEndDateAsString() {
		if (endDate != null) { 
			return outputFormat.format(endDate);
		}
		return "";
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void addUser(User u) {
		this.users.add(u);
		this.amountParticipants=+1;
	}

	public Room() {}

	public Room(int id, String name, String code, int amountParticipants, int maxAmountParticipants, Date initDate, Date endDate, boolean deleted) {
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
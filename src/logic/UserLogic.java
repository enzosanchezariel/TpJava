package logic;

import java.util.ArrayList;

import com.mysql.cj.util.StringUtils;

import data.UserDB;
import entities.User;


public class UserLogic {
	
	private UserDB db = new UserDB();
	
	public ArrayList<User> getAll(){
		return db.getAll();
	}
	
	public User getById(User u) {
		return db.getById(u);
	}
	
	public User getByEmail(User u) {
		return db.getByEmail(u);
	}
	
	public void save(User u) {
		db.save(u);
	}
	
	public void delete(User u) {
		db.delete(u);
	}
	
	public void update(User u) {
		db.update(u);
	}
	
	
	
	public User searchAndCompare(User u) {
		User userFromDB = db.getByEmail(u);
		if (userFromDB != null
				&& u.getEmail().equals(userFromDB.getEmail())
				&& u.getPassword().equals(userFromDB.getPassword())) {
			return userFromDB;
		}
		else {
			return null;
		}
	}
	
	public User validateRegisterAndReturnUser(User u, String confirmPassword) {
		if (
			StringUtils.isEmptyOrWhitespaceOnly(u.getPassword())
			|| StringUtils.isEmptyOrWhitespaceOnly(u.getName())
			|| StringUtils.isEmptyOrWhitespaceOnly(u.getSurname())
			|| StringUtils.isEmptyOrWhitespaceOnly(u.getEmail())
		) {
			return null;
		}
		if (!(u.getPassword().equals(confirmPassword))) {
			return null;
		}
		ArrayList<User> usersFromDB = db.getAll();
		for(User currentUser: usersFromDB) {
			if(u.getEmail().equalsIgnoreCase(currentUser.getEmail())) {
				return null;
			}
		}
		this.save(u);
		User userFromDB = this.getByEmail(u);
		return userFromDB;	
	}
	
	public User validateUpdateAndReturnUser(User u, String confirmPassword) {
		if (
			StringUtils.isEmptyOrWhitespaceOnly(u.getPassword())
			|| StringUtils.isEmptyOrWhitespaceOnly(u.getName())
			|| StringUtils.isEmptyOrWhitespaceOnly(u.getSurname())
			|| StringUtils.isEmptyOrWhitespaceOnly(u.getEmail())
		) {
			return null;
		}
		if (!(u.getPassword().equals(confirmPassword))) {
			return null;
		}
		ArrayList<User> usersFromDB = db.getAll();
		for(User currentUser: usersFromDB) {
			if(u.getEmail().equalsIgnoreCase(currentUser.getEmail()) && u.getId() != currentUser.getId()) {
				return null;
			}
		}
		this.update(u);
		User userFromDB = this.getById(u);
		return userFromDB;	
	}
}

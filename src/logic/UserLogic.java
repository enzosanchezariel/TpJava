package logic;

import java.util.ArrayList;

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
				&& !userFromDB.isDeleted()
				&& u.getEmail().equals(userFromDB.getEmail())
				&& u.getPassword().equals(userFromDB.getPassword())) {
			return userFromDB;
		}
		else {
			return null;
		}
	}
	
	public User validateRegisterAndReturnUser(User u, String confirmPassword) {
		if (!(u.getPassword().equals(confirmPassword))) {
			return null;
		}
		ArrayList<User> usersFromDB = db.getAll();
		for(User currentUser: usersFromDB) {
			if(u.getEmail().equalsIgnoreCase(currentUser.getEmail())) {
				return null;
			}
		}
		u.setDeleted(false);
		this.save(u);
		User userFromDB = this.getByEmail(u);
		return userFromDB;	
	}
}

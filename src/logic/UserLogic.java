package logic;

import java.util.ArrayList;

import entities.User;


public class UserLogic {
	public ArrayList<User> getAll(){
		// TODO
		ArrayList<User> a = new ArrayList<User>();
		
		a.add(new User(1, "Ricardo", "Darín", "darin.carp@gmail.com", "asdasd", false));
		a.add(new User(2, "Mark", "Zuckerberg", "area51.boy@meta.com", "dadada", false));
		a.add(new User(3, "Gustavo", "Cerati", "gustavo.stereo@yahoo.com", "americanBlind", false));
		a.add(new User(4, "Diego", "Maradona", "d10.maradona@hotmail.com", "footballHigh", false));
		
		return a;
	}
	
	public User getById(User u) {
		// TODO
		return new User();
	}
	
	public User searchAndCompare(User u) {
		// TODO
		User testUser = new User(5, "Mario", "Bros", "itsame.mario@yahoo.com", "solong", false);
		if (u.getEmail().equals(testUser.getEmail()) && u.getPassword().equals(testUser.getPassword())) {
			return testUser;
		}
		else {
			return null;
		}
	}
}

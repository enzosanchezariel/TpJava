package logic;

import java.util.LinkedList;

import entities.User;


public class Login {
	public LinkedList<User> getAll(){
		LinkedList<User> a = new LinkedList<User>();
		
		//TODO Falta paquete data. Por el momento una lista de prueba hardcodeada
		a.add(new User("tomasNOB","32234234"));
		a.add(new User("JuanCARC","ahdefr"));
		a.add(new User("pedro_acu","gogarala"));
		a.add(new User("Franco","dadada"));
		a.add(new User("Franccesco","fefefe"));
		a.add(new User("patri.grosa","grt334"));
		a.add(new User("Guada123","wdedfg"));
		a.add(new User("tomas1122","miperrito"));
		
		return a;
	}
}

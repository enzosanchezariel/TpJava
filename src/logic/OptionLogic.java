package logic;

import data.OptionDB;
import entities.Option;

public class OptionLogic {

	OptionDB optionDB = new OptionDB();
	
	public void save(Option o) {
		optionDB.save(o);
	}
	
}

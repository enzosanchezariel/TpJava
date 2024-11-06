package logic;

import data.QuestionDB;
import entities.Question;

public class QuestionLogic {
	
	QuestionDB questionDB = new QuestionDB();
	
	public Question save(Question q) {
		Question question = questionDB.save(q);
		return question;
	}
	
}

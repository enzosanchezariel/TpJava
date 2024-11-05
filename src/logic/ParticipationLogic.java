package logic;

import data.ParticipationDB;
import data.QuizDB;
import data.UserDB;
import entities.Participation;
import entities.Quiz;
import entities.User;

public class ParticipationLogic {
	
	ParticipationDB participationDB = new ParticipationDB();


	
	
	public Participation getParticipationByUserAndQuiz(User u, Quiz q) {
		return participationDB.getParticipationByUserAndQuiz(u, q);
	}

	public void saveParticipation(Participation p) {
		participationDB.saveParticipation(p);
	}
	
	public void updateParticipation(Participation p) {
		participationDB.update(p);
	}

}

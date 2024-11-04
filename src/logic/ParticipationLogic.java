package logic;

import data.ParticipationDB;
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

}

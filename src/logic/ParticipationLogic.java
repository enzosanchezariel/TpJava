package logic;

import data.ParticipationDB;
<<<<<<< Updated upstream
=======
import data.QuizDB;
import data.UserDB;
>>>>>>> Stashed changes
import entities.Participation;
import entities.Quiz;
import entities.User;

public class ParticipationLogic {
	
<<<<<<< Updated upstream
	ParticipationDB participationDB = new ParticipationDB();
=======
	private ParticipationDB participationDB = new ParticipationDB();
	private QuizDB quizDB = new QuizDB();
>>>>>>> Stashed changes

	
	
	public Participation getParticipationByUserAndQuiz(User u, Quiz q) {
<<<<<<< Updated upstream
		return participationDB.getParticipationByUserAndQuiz(u, q);
	}

	public void saveParticipation(Participation p) {
		participationDB.saveParticipation(p);
=======
		Participation participation = participationDB.getParticipationByUserAndQuiz(u, q);
		if(participation != null) {
			return participation;
		}else {
			return null;
		}

>>>>>>> Stashed changes
	}

}

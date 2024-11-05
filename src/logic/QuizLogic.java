package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.QuizDB;
import data.RoomDB;
import entities.Question;
import entities.Quiz;

public class QuizLogic {

	QuizDB quizDB = new QuizDB();
	
	public Quiz getById(Quiz q) {
		// Debe tener la Room a la que pertenece
		// Debe tener todas sus Question
		Quiz quiz = quizDB.getById(q);
		if (quiz != null) {
			RoomDB roomDB = new RoomDB();
			quiz.setRoom(roomDB.getById(quiz.getRoom()));
		}
		return quiz;
	}
}
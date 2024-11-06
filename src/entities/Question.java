package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private int id;
    private String questionText;
    private int correctAnswer;
    private ArrayList<String> options;
    private Quiz quiz;
    
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public ArrayList<String> getOptions() {
		return options;
	}
	
	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	
	public Question() {}
	
	public Question(int id, String questionText, int correctAnswer, ArrayList<String> options) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.correctAnswer = correctAnswer;
		this.options = options;
	}
}

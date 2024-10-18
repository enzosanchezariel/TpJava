package entities;

import java.util.Arrays;
import java.util.List;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public Question(String questionText, List<String> options) {
        this.questionText = questionText; 
        this.options = options.toArray(new String[0]); // Convierte la lista a un array
        this.correctAnswer = -1;
    }

	public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return Arrays.asList(options); // Convierte el array a una lista
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

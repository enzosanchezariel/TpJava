package entities;

public class Question {
	int idQuestion;
	int score;
	// como manejamos las opciones
	
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public Question() {}
	
	public Question( int idQuestion, int score) {
		this.idQuestion = idQuestion;
		this.score = score;
	}
	
}

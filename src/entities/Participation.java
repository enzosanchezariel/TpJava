package entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Participation {
	
	// Participation actua tambien como intento en curso
	
	User user;
	Quiz quiz;
	int amountRight;
	
	// No hace falta guardar attemptDateAndHour en la bdd.
	// Es nomas para verificar el tiempo que le queda al usuario para terminar el Quiz.
	// Bah, si quieren pueden hacerlo.
	Timestamp attemptDateAndHour;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getAmountRight() {
		return amountRight;
	}

	public void setAmountRight(int amountRight) {
		this.amountRight = amountRight;
	}

	public Timestamp getAttemptDateAndHour() {
		return attemptDateAndHour;
	}

	public void setAttemptDateAndHour(Timestamp attemptDateAndHour) {
		this.attemptDateAndHour = attemptDateAndHour;
	}

	public Participation() {}
	
	public Participation(User user, Quiz quiz, int amountRight, Timestamp attemptDateAndHour) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.amountRight = amountRight;
		this.attemptDateAndHour = attemptDateAndHour;
	}
	
	public boolean isValid() {
		long maxDurationInMillis = quiz.getMaxDuration().getTime();
		
		Timestamp attemptEndTime = new Timestamp(attemptDateAndHour.getTime() + maxDurationInMillis);
		
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		
		return currentTime.before(attemptEndTime);
	}
}
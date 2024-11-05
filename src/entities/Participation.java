package entities;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Participation {
	
	// Participation actua tambien como intento en curso
	
	User user;
	Quiz quiz;
	int amountRight;
	
	// No hace falta guardar attemptDateAndHour en la bdd.
	// Es nomas para verificar el tiempo que le queda al usuario para terminar el Quiz.
	// Bah, si quieren pueden hacerlo.
	LocalDateTime attemptDateAndHour;

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

	public LocalDateTime getAttemptDateAndHour() {
		return attemptDateAndHour;
	}

	public void setAttemptDateAndHour(LocalDateTime attemptDateAndHour) {
		this.attemptDateAndHour = attemptDateAndHour;
	}

	public Participation() {}
	
	public Participation(User user, Quiz quiz, int amountRight, LocalDateTime attemptDateAndHour) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.amountRight = amountRight;
		this.attemptDateAndHour = attemptDateAndHour;
	}
	
	public boolean isValid() {
		LocalDateTime attDT = attemptDateAndHour;
		LocalTime durDT = quiz.getMaxDuration().toLocalTime();
		
		LocalDateTime endDT = attDT.plusHours(durDT.getHour()).plusMinutes(durDT.getMinute()).plusSeconds(durDT.getSecond());
		
		return LocalDateTime.now().isBefore(endDT);
	}
}
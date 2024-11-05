package entities;

public class Response {
	private Question question;
	private int userResponse;
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(Integer userResponse) {
		this.userResponse = userResponse;
	}
	
	public boolean isCorrect() {
		return userResponse == question.getCorrectAnswer();
	}

	public Response() {}

	public Response(Question question, Integer userResponse) {
		super();
		this.question = question;
		this.userResponse = userResponse;
	}
}
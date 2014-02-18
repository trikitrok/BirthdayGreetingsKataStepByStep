package main.core;

public class Greetings {
	private String subject;
	private String message;

	public Greetings(Employee employee) {
		this.subject = "Happy Birthday!";
		this.message = "Happy Birthday, dear %NAME%!".replace(
				"%NAME%", employee.getFirstName());
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}
}

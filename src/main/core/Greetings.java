package main.core;

public class Greetings {
	private final String subject;
	private final String message;

	public Greetings(String subject, String message) {
		this.subject = subject;
		this.message = message;
	}
	
	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}
}

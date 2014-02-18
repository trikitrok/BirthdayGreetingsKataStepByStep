package main.core;

public class GreetingsFor {

	private String subject;
	private String message;
	private Employee employee;

	public GreetingsFor(Employee employee) {
		this.employee = employee;
	}

	public GreetingsFor birthday() {
		this.subject = "Happy Birthday!";
		this.message = "Happy Birthday, dear %NAME%!".replace("%NAME%",
				employee.getFirstName());

		return this;
	}

	public Greetings create() {
		return new Greetings(subject, message);
	}
}

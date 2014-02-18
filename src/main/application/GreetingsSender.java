package main.application;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import main.core.Employee;

public interface GreetingsSender {
	public void sendGreetingsTo(Employee employee) throws AddressException,
			MessagingException;
}

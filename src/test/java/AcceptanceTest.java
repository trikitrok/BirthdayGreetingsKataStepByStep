package test;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import application.BirthdayService;
import core.EmployeesRepository;
import core.OurDate;

import org.junit.Before;
import org.junit.Test;

import employee_repository_adapters.FileEmployeesRepository;
import greetings_sender_adapters.SmtpGreetingsSender;
import static org.junit.Assert.*;

public class AcceptanceTest {

	private static final int SMTP_PORT = 25;
	private static final String SMTP_HOST = "localhost";
	private List<Message> messagesSent;
	private BirthdayService service;
	private SmtpGreetingsSender messageSender;
	private EmployeesRepository employeesRepository;

	@Before
	public void setUp() throws Exception {
		messagesSent = new ArrayList<Message>();

		employeesRepository = new FileEmployeesRepository(
				"src/test/resources/employee_data.txt");

		messageSender = new SmtpGreetingsSender(SMTP_HOST, SMTP_PORT) {
			@Override
			protected void sendMessage(Message msg) throws MessagingException {
				messagesSent.add(msg);
			}
		};

		service = new BirthdayService(employeesRepository, messageSender);
	}

	@Test
	public void baseScenario() throws Exception {

		service.sendGreetings(new OurDate("2008/10/08"));

		assertEquals("message not sent?", 1, messagesSent.size());
		Message message = messagesSent.get(0);
		assertEquals("Happy Birthday, dear John!", message.getContent());
		assertEquals("Happy Birthday!", message.getSubject());
		assertEquals(1, message.getAllRecipients().length);
		assertEquals("john.doe@foobar.com",
				message.getAllRecipients()[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		service.sendGreetings(new OurDate("2008/01/01"));

		assertEquals("what? messages?", 0, messagesSent.size());
	}
}

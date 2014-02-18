package main.application;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import main.core.Employee;
import main.core.EmployeesRepository;
import main.core.OurDate;

public class BirthdayService {

	public BirthdayService(EmployeesRepository employeesRepository, SmtpMessageSender messageSender) {
		this.employeesRepository = employeesRepository;
		this.messageSender = messageSender;
	}

	private EmployeesRepository employeesRepository;
	private SmtpMessageSender messageSender;

	public void sendGreetings(OurDate ourDate, String smtpHost, int smtpPort)
			throws AddressException, MessagingException {
		
		List<Employee> employeesWithBirthdayToday = this.employeesRepository
				.findEmployeesWhoseBirthdayIs(ourDate);

		for (Employee employee : employeesWithBirthdayToday) {
			sendGreetingsTo(employee);
		}
	}

	private void sendGreetingsTo(Employee employee) throws AddressException,
			MessagingException {
		
		String subject = "Happy Birthday!";
		String greetingsMessage = "Happy Birthday, dear %NAME%!".replace(
				"%NAME%", employee.getFirstName());

		String recipient = employee.getEmail();
		
		this.messageSender.sendMessage("sender@here.com",
				subject, greetingsMessage, recipient);
	}
}

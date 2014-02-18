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

	public BirthdayService(EmployeesRepository employeesRepository) {
		this.employeesRepository = employeesRepository;
	}

	private EmployeesRepository employeesRepository;
	private SmtpMessageSender messageSender;

	public void sendGreetings(OurDate ourDate, String smtpHost, int smtpPort)
			throws AddressException, MessagingException {

		List<Employee> employeesWithBirthdayToday = this.employeesRepository
				.findEmployeesWhoseBirthdayIs(ourDate);

		for (Employee employee : employeesWithBirthdayToday) {
			String recipient = employee.getEmail();
			String body = "Happy Birthday, dear %NAME%!".replace("%NAME%",
					employee.getFirstName());
			String subject = "Happy Birthday!";
			sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body,
					recipient);
		}
	}

	private void sendMessage(String smtpHost, int smtpPort, String sender,
			String subject, String body, String recipient)
			throws AddressException, MessagingException {
		
		this.messageSender = new SmtpMessageSender(smtpHost, smtpPort);
		
		Session session = createMailSession(smtpHost, smtpPort);

		Message msg = constructMessage(sender, subject, body, recipient,
				session);

		// Send the message
		sendMessage(msg);
	}

	private Message constructMessage(String sender, String subject,
			String body, String recipient, Session session)
			throws MessagingException, AddressException {
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(sender));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
				recipient));
		msg.setSubject(subject);
		msg.setText(body);
		return msg;
	}

	private Session createMailSession(String smtpHost, int smtpPort) {
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		Session session = Session.getDefaultInstance(props, null);
		return session;
	}

	// made protected for testing :-(
	protected void sendMessage(Message msg) throws MessagingException {
		Transport.send(msg);
	}
}

package main.application;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import main.core.Employee;

public class SmtpMessageSender {

	public int smtpPort;
	public String smtpHost;

	public SmtpMessageSender(String smtpHost, int smtpPort) {
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
	}

	public void sendGreetingsTo(Employee employee) throws AddressException,
			MessagingException {

		String subject = "Happy Birthday!";
		String greetingsMessage = "Happy Birthday, dear %NAME%!".replace(
				"%NAME%", employee.getFirstName());

		String recipient = employee.getEmail();

		sendMessage("sender@here.com", subject, greetingsMessage, recipient);
	}

	private void sendMessage(String sender, String subject, String body,
			String recipient) throws AddressException, MessagingException {

		Session session = createMailSession();

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

	private Session createMailSession() {
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

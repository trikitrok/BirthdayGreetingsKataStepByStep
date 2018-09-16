package greetings_sender_adapters;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import core.Employee;
import core.Greetings;
import core.GreetingsNotSent;
import core.GreetingsSender;

public class SmtpGreetingsSender implements GreetingsSender {

	private final int smtpPort;
	private final String smtpHost;
	private final String SENDER = "sender@here.com";

	public SmtpGreetingsSender(String smtpHost, int smtpPort) {
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
	}

	public void send(Employee employee, Greetings greetinsgs) {
		sendMessage(greetinsgs, SENDER, employee.getEmail());
	}

	private void sendMessage(Greetings greetings, String sender,
			String recipient) {

		Session session = createMailSession();
		try {
			Message msg = constructMessage(sender, greetings.getSubject(),
					greetings.getMessage(), recipient, session);

			sendMessage(msg);
		} catch (AddressException e) {
			throw new GreetingsNotSent();
		} catch (MessagingException e) {
			throw new GreetingsNotSent();
		}
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

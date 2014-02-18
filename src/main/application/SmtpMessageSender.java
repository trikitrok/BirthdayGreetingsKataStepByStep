package main.application;

public class SmtpMessageSender {

	private int smtpPort;
	private String smtpHost;

	public SmtpMessageSender(String smtpHost, int smtpPort) {
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
	}

}

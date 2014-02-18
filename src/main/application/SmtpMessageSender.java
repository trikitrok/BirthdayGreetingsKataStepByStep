package main.application;

public class SmtpMessageSender {

	public int smtpPort;
	public String smtpHost;

	public SmtpMessageSender(String smtpHost, int smtpPort) {
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
	}

}

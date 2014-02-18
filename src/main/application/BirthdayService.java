package main.application;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import main.core.Employee;
import main.core.EmployeesRepository;
import main.core.OurDate;

public class BirthdayService {

	public BirthdayService(EmployeesRepository employeesRepository, GreetingsSender messageSender) {
		this.employeesRepository = employeesRepository;
		this.greetingsSender = messageSender;
	}

	private EmployeesRepository employeesRepository;
	private GreetingsSender greetingsSender;

	public void sendGreetings(OurDate ourDate, String smtpHost, int smtpPort)
			throws AddressException, MessagingException {
		
		List<Employee> employeesWithBirthdayToday = this.employeesRepository
				.findEmployeesWhoseBirthdayIs(ourDate);

		for (Employee employee : employeesWithBirthdayToday) {
			this.greetingsSender.sendGreetingsTo(employee);
		}
	}
}

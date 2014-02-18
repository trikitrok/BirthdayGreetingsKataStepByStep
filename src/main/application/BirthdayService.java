package main.application;

import java.util.List;

import main.core.Employee;
import main.core.EmployeesRepository;
import main.core.OurDate;

public class BirthdayService {

	private final EmployeesRepository employeesRepository;
	private final GreetingsSender greetingsSender;
	
	public BirthdayService(EmployeesRepository employeesRepository, GreetingsSender messageSender) {
		this.employeesRepository = employeesRepository;
		this.greetingsSender = messageSender;
	}

	public void sendGreetings(OurDate today, String smtpHost, int smtpPort) {
		
		List<Employee> employeesWithBirthdayToday = this.employeesRepository
				.findEmployeesWhoseBirthdayIs(today);

		for (Employee employee : employeesWithBirthdayToday) {
			this.greetingsSender.sendGreetingsTo(employee);
		}
	}
}

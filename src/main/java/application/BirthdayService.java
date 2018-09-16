package application;

import java.util.List;

import core.Employee;
import core.EmployeesRepository;
import core.GreetingsFor;
import core.GreetingsSender;
import core.OurDate;

public class BirthdayService {

	private final EmployeesRepository employeesRepository;
	private final GreetingsSender greetingsSender;
	
	public BirthdayService(EmployeesRepository employeesRepository, GreetingsSender messageSender) {
		this.employeesRepository = employeesRepository;
		this.greetingsSender = messageSender;
	}

	public void sendGreetings(OurDate today) {
		
		List<Employee> employeesWithBirthdayToday = this.employeesRepository
				.findEmployeesWhoseBirthdayIs(today);

		for (Employee employee : employeesWithBirthdayToday) {
			this.greetingsSender.send(employee, new GreetingsFor(employee).birthday().build());
		}
	}
}

package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeesRepository implements EmployeesRepository {
	private String fileName;

	public FileEmployeesRepository(String fileName) {
		this.fileName = fileName;
	}

	public List<Employee> findEmployeesWhoseBirthdayIs(OurDate today)
			throws EmployeesRepositoryNotAccessible, EmployeeNotAccessible {

		List<Employee> employeesWithBirthdayToday = new ArrayList<Employee>();

		try {
			BufferedReader in = new BufferedReader(
					new FileReader(this.fileName));

			String employeeRecord = in.readLine(); // skip header

			while ((employeeRecord = in.readLine()) != null) {
				Employee employee = extractEmployeeFrom(employeeRecord);

				if (employee.isBirthday(today)) {
					employeesWithBirthdayToday.add(employee);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			throw new EmployeesRepositoryNotAccessible();
		} catch (IOException e) {
			throw new EmployeesRepositoryNotAccessible();
		}

		return employeesWithBirthdayToday;
	}

	private Employee extractEmployeeFrom(String str)
			throws EmployeeNotAccessible {
		try {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0],
					employeeData[2], employeeData[3]);
			return employee;
		} catch (ParseException e) {
			throw new EmployeeNotAccessible();
		}
	}
}

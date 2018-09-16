package employee_repository_adapters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import core.Employee;
import core.EmployeeDataNotAccessible;
import core.EmployeesRepository;
import core.EmployeesRepositoryNotAccessible;
import core.OurDate;

public class FileEmployeesRepository implements EmployeesRepository {
	private String fileName;

	public FileEmployeesRepository(String fileName) {
		this.fileName = fileName;
	}

	public List<Employee> findEmployeesWhoseBirthdayIs(OurDate today) {

		List<Employee> employees = getAllEmployees();

		return filterEmployeesWhoseBirthdayIs(today, employees);
	}

	private List<Employee> filterEmployeesWhoseBirthdayIs(OurDate today,
			List<Employee> employees) {
		List<Employee> employeesWithBirthdayToday = new ArrayList<Employee>();

		for (Employee employee : employees) {
			if (employee.isBirthday(today)) {
				employeesWithBirthdayToday.add(employee);
			}
		}
		return employeesWithBirthdayToday;
	}

	private List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();

		try {
			BufferedReader employeesFileReader = createEmployeesFileReader();

			String employeeRecord = employeesFileReader.readLine(); // skip
																	// header

			while ((employeeRecord = employeesFileReader.readLine()) != null) {
				Employee employee = extractEmployeeFrom(employeeRecord);

				employees.add(employee);
			}
			employeesFileReader.close();
		} catch (IOException e) {
			throw new EmployeesRepositoryNotAccessible();
		}
		return employees;
	}

	private BufferedReader createEmployeesFileReader()
			throws FileNotFoundException {
		FileReader fileReader;
		try {
			fileReader = new FileReader(this.fileName);
		} catch (FileNotFoundException e) {
			throw new EmployeesRepositoryNotAccessible();
		}
		return new BufferedReader(fileReader);
	}

	private Employee extractEmployeeFrom(String str)
			throws EmployeeDataNotAccessible {
		try {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0],
					employeeData[2], employeeData[3]);
			return employee;
		} catch (ParseException e) {
			throw new EmployeeDataNotAccessible();
		}
	}
}

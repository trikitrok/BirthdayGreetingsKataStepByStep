package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository {
	private String fileName;

	public FileEmployeeRepository(String fileName) {
		this.fileName = fileName;
	}

	public List<Employee> findEmployeesWhoseBirthdayIs(OurDate today) throws FileNotFoundException, IOException,
			ParseException {
		List<Employee> employeesWithBirthdayToday = new ArrayList<Employee>();
    	
        BufferedReader in = new BufferedReader(new FileReader(this.fileName));
        String str = "";
        str = in.readLine(); // skip header
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0],
                    employeeData[2], employeeData[3]);
            if (employee.isBirthday(today)) {
            	employeesWithBirthdayToday.add(employee);
            }
        }
        in.close();
		return employeesWithBirthdayToday;
	}
}

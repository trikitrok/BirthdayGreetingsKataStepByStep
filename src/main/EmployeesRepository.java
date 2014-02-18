package main;

import java.util.List;

public interface EmployeesRepository {
	public List<Employee> findEmployeesWhoseBirthdayIs(OurDate today)
			throws EmployeesRepositoryNotAccessible, EmployeeNotAccessible;
}

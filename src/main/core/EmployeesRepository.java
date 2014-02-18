package main.core;

import java.util.List;

public interface EmployeesRepository {
	public List<Employee> findEmployeesWhoseBirthdayIs(OurDate today);
}

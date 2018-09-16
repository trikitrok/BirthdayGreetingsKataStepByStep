package core;

import java.util.List;

public interface EmployeesRepository {
	List<Employee> findEmployeesWhoseBirthdayIs(OurDate today);
}

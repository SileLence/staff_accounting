package dv.trunov.webapp.repositories;

import dv.trunov.webapp.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}

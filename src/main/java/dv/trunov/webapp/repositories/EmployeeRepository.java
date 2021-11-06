package dv.trunov.webapp.repositories;

import dv.trunov.webapp.domain.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository
        extends CrudRepository<EmployeeEntity, Integer> {
}

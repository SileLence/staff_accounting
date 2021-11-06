package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.EmployeeEntity;
import dv.trunov.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity add(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    public List<EmployeeEntity> findAll() {
        return (ArrayList<EmployeeEntity>) employeeRepository.findAll();
    }

    public EmployeeEntity findById(Integer id) {
        Optional<EmployeeEntity> optEmployee = employeeRepository.findById(id);
        return optEmployee.get();
    }

    public ResponseEntity deleteById(Integer id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Employee was deleted");
    }
}

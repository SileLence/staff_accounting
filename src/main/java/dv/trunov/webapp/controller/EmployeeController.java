package dv.trunov.webapp.controller;

import dv.trunov.webapp.domain.EmployeeEntity;
import dv.trunov.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/findAll")
	public List<EmployeeEntity> getAll() {
		return employeeService.findAll();
	}

	@GetMapping("/find")
	public EmployeeEntity getById(@RequestParam Integer id) {
		return employeeService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity addEmployee(@RequestBody EmployeeEntity employee) {
		employeeService.add(employee);
		return ResponseEntity.ok("Employee was added.");
	}

	@DeleteMapping("/delete")
	public ResponseEntity deleteEmployee(@RequestParam Integer id) {
		return employeeService.deleteById(id);
	}
}

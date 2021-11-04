package dv.trunov.webapp;

import dv.trunov.webapp.domain.Employee;
import dv.trunov.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/employee")
	public String main(Map<String, Object> model) {
		Iterable<Employee> employees = employeeRepository.findAll();
		model.put("employees", employees);
		return "employee";
	}

	@PostMapping("/employee")
	public String add(@RequestParam String firstname,
					  @RequestParam String surname,
					  @RequestParam String address,
					  @RequestParam String email,
					  @RequestParam String phoneNumber,
					  Map<String, Object> model) {
		Employee employee = new Employee(
				firstname,
				surname,
				address,
				email,
				phoneNumber);
		employeeRepository.save(employee);

		Iterable<Employee> employees = employeeRepository.findAll();
		model.put("employees", employees);
		return "employee";
	}
//
//	@PostMapping("/finder")
//	public String find(@RequestParam Integer id, Map<String, Object> model) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//	}
}

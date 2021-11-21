package dv.trunov.webapp.controller;

import dv.trunov.webapp.dto.UserCreationDto;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users")
public class UsersController {
	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<Object> createUser(
			@RequestBody UserCreationDto user) {
		userService.add(user);
		return ResponseEntity.ok("User was added successfully.");
	}

	@GetMapping
	public ResponseEntity<Object> getAllUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/info/{id}")
	public ResponseEntity<Object> getUserInfoById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findInfoById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/category/{name}")
	public ResponseEntity<Object> getUsersByCategory(
			@PathVariable String name) {
		return ResponseEntity.ok(userService.findByCategory(name));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(
			@PathVariable("id") Integer userId,
			@RequestBody UserCreationDto user) {
		try {
			userService.update(userId, user);
			return ResponseEntity.ok("User with ID:"
					+ userId
					+ " was updated successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/category")
	public ResponseEntity<Object> addUserCategory(
			@RequestParam Integer userId,
			@RequestParam String categoryName) {
		try {
			userService.addCategory(userId, categoryName);
			return ResponseEntity.ok("Category " + categoryName
					+ " was added to User with ID:"
					+ userId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
		try {
			userService.deleteById(id);
			return ResponseEntity.ok("User with ID:" + id
					+ " was deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}

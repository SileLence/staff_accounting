package dv.trunov.webapp.controller;

import dv.trunov.webapp.dto.UserCreationDto;
import dv.trunov.webapp.service.UserService;
import dv.trunov.webapp.validation.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path="/users")
public class UsersController {
	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@Validated(Marker.OnCreate.class)
	public ResponseEntity<Object> createUser(
			@Valid @RequestBody UserCreationDto user,
			@RequestParam String category) {
		userService.add(user, category);
		return ResponseEntity.ok("User was added successfully.");
	}

	@GetMapping
	public ResponseEntity<Object> getAllUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
		return ResponseEntity.ok(userService.findById(id));
	}

	@GetMapping("/info/{id}")
	public ResponseEntity<Object> getUserInfoById(@PathVariable Integer id) {
		return ResponseEntity.ok(userService.findInfoById(id));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<Object> getUsersByCategory(
			@PathVariable String category) {
		return ResponseEntity.ok(userService.findByCategory(category));
	}

	@PutMapping("/{id}")
	@Validated(Marker.OnUpdate.class)
	public ResponseEntity<Object> updateUser(
			@PathVariable("id") Integer userId,
			@Valid @RequestBody UserCreationDto user) {
		userService.update(userId, user);
		return ResponseEntity.ok(
				"User with ID:" + userId + " was updated successfully.");
	}

	@PutMapping("/category")
	public ResponseEntity<Object> updateUserCategory(
			@RequestParam("id") Integer userId,
			@RequestParam String category) {
		userService.updateCategory(userId, category);
		return ResponseEntity.ok("User's category was updated.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
		userService.deleteById(id);
		return ResponseEntity.ok(
				"User with ID:" + id + " was deleted successfully.");
	}
}

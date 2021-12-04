package dv.trunov.webapp.controller;

import dv.trunov.webapp.dto.UserCreationDto;
import dv.trunov.webapp.dto.UserDto;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.service.UserService;
import dv.trunov.webapp.validation.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
		try {
			userService.add(user, category);
			return ResponseEntity.ok("User was added successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
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
		List<UserDto> users = userService.findByCategory(name);
		if (users.isEmpty()) {
			return ResponseEntity.ok(
					"Users not found or Category is not exists.");
		}
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	@Validated(Marker.OnUpdate.class)
	public ResponseEntity<Object> updateUser(
			@PathVariable("id") Integer userId,
			@Valid @RequestBody UserCreationDto user) {
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
	public ResponseEntity<Object> updateUserCategory(
			@RequestParam("id") Integer userId,
			@RequestParam String category) {
		try {
			userService.updateCategory(userId, category);
			return ResponseEntity.ok("User's category was updated.");
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

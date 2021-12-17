package dv.trunov.webapp.controller;

import dv.trunov.webapp.dto.UserCreationDto;
import dv.trunov.webapp.dto.UserDto;
import dv.trunov.webapp.dto.UserInfoDto;
import dv.trunov.webapp.service.UserService;
import dv.trunov.webapp.validation.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/users")
public class UsersController {
	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@Validated(Marker.OnCreate.class)
	public ResponseEntity<String> createUser(
			@Valid @RequestBody UserCreationDto user,
			@RequestParam String category) {
		userService.addUser(user, category);
		return new ResponseEntity<>(
				"User was added successfully.",
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
		return ResponseEntity.ok(userService.findUserById(id));
	}

	@GetMapping("/info/{id}")
	public ResponseEntity<UserInfoDto> getUserInfoById(
			@PathVariable Integer id) {
		return ResponseEntity.ok(userService.findUserInfoById(id));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<UserDto>> getUsersByCategory(
			@PathVariable String category) {
		return ResponseEntity.ok(userService.findUsersByCategory(category));
	}

	@PutMapping("/{id}")
	@Validated(Marker.OnUpdate.class)
	public ResponseEntity<String> updateUser(
			@PathVariable("id") Integer userId,
			@Valid @RequestBody UserCreationDto user) {
		userService.updateUser(userId, user);
		return ResponseEntity.ok(String.format(
				"User with ID: %d was updated successfully.", userId)
		);
	}

	@PutMapping("/category")
	public ResponseEntity<String> updateUserCategory(
			@RequestParam("id") Integer userId,
			@RequestParam String category) {
		userService.updateUserCategory(userId, category);
		return ResponseEntity.ok("User's category was updated.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		userService.deleteUserById(id);
		return ResponseEntity.ok(String.format(
				"User with ID: %d was deleted successfully.", id)
		);
	}
}

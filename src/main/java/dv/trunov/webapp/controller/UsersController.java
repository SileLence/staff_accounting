package dv.trunov.webapp.controller;

import dv.trunov.webapp.domain.UserEntity;
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
	public ResponseEntity<Object> addUser(@RequestBody UserEntity user) {
		userService.add(user);
		return ResponseEntity.ok("User was added.");
	}

	@GetMapping
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/model")
	public ResponseEntity<Object> findModelById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findModelById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/category")
	public ResponseEntity<Object> findByCategory(
			@RequestParam String category) {
		return ResponseEntity.ok(userService.findByCategory(category));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id,
										 @RequestBody UserEntity user) {
		try {
			userService.update(id, user);
			return ResponseEntity.ok("User with ID:"
					+ id
					+ " was updated successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/category")
	public ResponseEntity<Object> addCategory(
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

package dv.trunov.webapp.controller;

import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<Object> addUser(
			@RequestBody UserEntity user) {
		userService.add(user);
		return ResponseEntity.ok("User was added.");
	}

	@GetMapping("/users")
	public List<UserEntity> findAll() {
		return userService.findAll();
	}

	@GetMapping("/user{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/user{id}/model")
	public ResponseEntity<Object> findModelById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findModelById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/user")
	public ResponseEntity<Object> update(@RequestBody UserEntity user) {
		try {
			userService.update(user);
			return ResponseEntity.ok("User with ID:"
					+ user.getId()
					+ " was updated successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete{id}")
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

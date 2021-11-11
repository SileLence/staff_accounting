package dv.trunov.webapp.controller;

import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<Object> addUser(@RequestBody UserEntity user) {
		userService.add(user);
		return ResponseEntity.ok("User was added.");
	}

	@GetMapping("/all")
	public List<UserEntity> findAll() {
		return userService.findAll();
	}

	@GetMapping("/find{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/find{id}/model")
	public ResponseEntity<Object> findModelById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(userService.findModelById(id));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/findByCategory")
	public ResponseEntity<Object> findByCategory(@RequestParam String name) {
		try {
			return new ResponseEntity<>(
					userService.findByCategory(name),
					HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update")
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

	@PutMapping("/addCategory")
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

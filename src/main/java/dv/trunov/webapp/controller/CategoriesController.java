package dv.trunov.webapp.controller;

import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(
            @RequestParam String category) {
        try {
            categoryService.add(category);
            return ResponseEntity.ok("Category was added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> getCategoryByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(categoryService.findByName(name));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Integer id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.ok("Category with ID:" + id
                    + " was deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

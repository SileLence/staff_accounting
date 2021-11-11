package dv.trunov.webapp.controller;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody CategoryEntity category) {
        categoryService.add(category);
        return ResponseEntity.ok("Category was added.");
    }

    @GetMapping("/all")
    public List<CategoryEntity> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/find{name}")
    public ResponseEntity<Object> findByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(categoryService.findByName(name));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

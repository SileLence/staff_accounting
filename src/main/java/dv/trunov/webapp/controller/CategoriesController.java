package dv.trunov.webapp.controller;

import dv.trunov.webapp.dto.CategoryCreationDto;
import dv.trunov.webapp.dto.CategoryDto;
import dv.trunov.webapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Validated
    public ResponseEntity<String> createCategory(
            @Valid @RequestBody CategoryCreationDto category) {
        categoryService.add(category);
        return new ResponseEntity<>(
                "The category was added successfully.",
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(
            @PathVariable String name) {
        CategoryDto category = categoryService.findByName(name);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok(String.format(
                "The category with ID: %d was deleted successfully.", id)
        );
    }
}

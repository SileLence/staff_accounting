package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity add(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public List<CategoryEntity> findAll() {
        return (ArrayList<CategoryEntity>) categoryRepository.findAll();
    }

    public CategoryEntity findByName(String name)
            throws ResourceNotFoundException {
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(name);
        return optCategory.orElseThrow(
                () -> new ResourceNotFoundException("Category not found."));
    }
}

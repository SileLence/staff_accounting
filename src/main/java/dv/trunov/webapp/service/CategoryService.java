package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.exception.ResourceBusyException;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.repositories.CategoryRepository;
import dv.trunov.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

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

    public void delete(Integer id)
            throws ResourceNotFoundException, ResourceBusyException {
        Optional<CategoryEntity> optCategory = categoryRepository.findById(id);
        if (optCategory.isPresent()) {
            List<UserEntity> users = userRepository
                    .findByCategory(optCategory.get().getName());
            if (users.isEmpty()) {
                throw new ResourceBusyException("Category is in use.");
            } else {
                categoryRepository.delete(optCategory.get());
            }
        } else {
            throw new ResourceNotFoundException("Category not found");
        }
    }
}

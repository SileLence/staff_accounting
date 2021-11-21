package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.dto.CategoryDto;
import dv.trunov.webapp.dto.Mapper;
import dv.trunov.webapp.exception.ResourceBusyException;
import dv.trunov.webapp.exception.ResourceExistsException;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.repositories.CategoryRepository;
import dv.trunov.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void add(String category)
            throws ResourceExistsException, IllegalArgumentException {
        if (category == null || "".equals(category)) {
            throw new IllegalArgumentException("Invalid category name.");
        }
        Optional<CategoryEntity> categoryEntity
                = categoryRepository.findByName(category);
        if (categoryEntity.isPresent()) {
            throw new ResourceExistsException("Category already exists.");
        }
        categoryRepository.save(Mapper.toCategoryEntity(category));
    }

    public List<CategoryDto> findAll() {
        List<CategoryEntity> categories
                = (ArrayList<CategoryEntity>) categoryRepository.findAll();
        return categories.stream()
                .map(Mapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto findByName(String name)
            throws ResourceNotFoundException {
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(name);
        return Mapper.toCategoryDto(optCategory.orElseThrow(
                () -> new ResourceNotFoundException("Category not found.")));
    }

    public void delete(Integer id)
            throws ResourceNotFoundException, ResourceBusyException {
        Optional<CategoryEntity> optCategory = categoryRepository.findById(id);
        if (optCategory.isPresent()) {
            List<UserEntity> users = userRepository
                    .findByCategory(optCategory.get().getName());
            if (!users.isEmpty()) {
                throw new ResourceBusyException("Category is in use.");
            }
            categoryRepository.delete(optCategory.get());
        } else {
            throw new ResourceNotFoundException("Category not found.");
        }
    }
}

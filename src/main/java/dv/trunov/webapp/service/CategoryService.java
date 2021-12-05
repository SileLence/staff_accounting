package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.dto.CategoryCreationDto;
import dv.trunov.webapp.dto.CategoryDto;
import dv.trunov.webapp.dto.Mapper;
import dv.trunov.webapp.repositories.CategoryRepository;
import dv.trunov.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public void add(CategoryCreationDto category) {
        Optional<CategoryEntity> categoryEntity
                = categoryRepository.findByName(category.getName());
        if (categoryEntity.isPresent()) {
            throw new DataIntegrityViolationException(
                    "Category already exists.");
        }
        categoryRepository.save(Mapper.toCategoryEntity(category));
    }

    public List<CategoryDto> findAll() {
        List<CategoryEntity> categories
                = (ArrayList<CategoryEntity>) categoryRepository.findAll();
        return categories.stream()
                .map(Mapper::toCategoryDto)
                .sorted(Comparator.comparingInt(CategoryDto::getId))
                .collect(Collectors.toList());
    }

    public CategoryDto findByName(String name) {
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(name);
        return Mapper.toCategoryDto(optCategory.orElseThrow(
                () -> new NoSuchElementException("Category not found.")));
    }

    public void delete(Integer id) {
        Optional<CategoryEntity> optCategory = categoryRepository.findById(id);
        if (optCategory.isEmpty()) {
            throw new NoSuchElementException("Category not found.");
        }
        CategoryEntity category = optCategory.get();
        List<UserEntity> users = userRepository
                .findByCategory(category.getName());
        if (users.size() > 0) {
            throw new DataIntegrityViolationException(
                    "Unable be delete. The category is used.");
        }
        categoryRepository.delete(category);
    }
}

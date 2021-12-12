package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.dto.Mapper;
import dv.trunov.webapp.dto.UserCreationDto;
import dv.trunov.webapp.dto.UserDto;
import dv.trunov.webapp.dto.UserInfoDto;
import dv.trunov.webapp.repositories.CategoryRepository;
import dv.trunov.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public void add(UserCreationDto user, String category) {
        UserEntity userEntity = Mapper.toUserEntity(user);
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(category);
        if (optCategory.isEmpty()) {
            throw new NoSuchElementException("Category not found.");
        }
        userEntity.setCategory(optCategory.get());
        userRepository.save(userEntity);
    }

    public List<UserDto> findAll() {
        List<UserEntity> users
                = (ArrayList<UserEntity>) userRepository.findAll();
        return users.stream()
                .map(Mapper::toUserDto)
                .sorted(Comparator.comparingInt(UserDto::getId))
                .collect(Collectors.toList());
    }

    public UserDto findById(Integer id) {
        Optional<UserEntity> optUser = userRepository.findById(id);
        return Mapper.toUserDto(optUser.orElseThrow(
                () -> new NoSuchElementException("User not found.")));
    }

    public UserInfoDto findInfoById(Integer id) {
        Optional<UserEntity> optUser = userRepository.findById(id);
        return Mapper.toUserInfoDto(optUser.orElseThrow(
                () -> new NoSuchElementException("User not found.")));
    }

    public List<UserDto> findByCategory(String category) {
        if (categoryRepository.findByName(category).isEmpty()) {
            throw new NoSuchElementException("Category not found.");
        }
        List<UserDto> users = userRepository.findByCategory(category)
                .stream()
                .map(Mapper::toUserDto)
                .sorted(Comparator.comparingInt(UserDto::getId))
                .collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new NoSuchElementException(
                    "No users with category: " + category + "."
            );
        }
        return users;
    }

    public void update(Integer userId, UserCreationDto user) {
        Optional<UserEntity> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NoSuchElementException("User not found.");
        }
        UserEntity updatedUser = optUser.get();
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        userRepository.save(updatedUser);
    }

    public void updateCategory(Integer userId, String categoryName) {
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(categoryName);
        Optional<UserEntity> optUser
                = userRepository.findById(userId);
        if (optCategory.isEmpty()) {
            throw new NoSuchElementException("Category not found.");
        }
        if (optUser.isEmpty()) {
            throw new NoSuchElementException("User not found.");
        }
        UserEntity user = optUser.get();
        user.setCategory(optCategory.get());
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("User not found.");
        }
        userRepository.deleteById(id);
    }
}

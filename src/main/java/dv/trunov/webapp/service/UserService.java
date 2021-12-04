package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.dto.Mapper;
import dv.trunov.webapp.dto.UserCreationDto;
import dv.trunov.webapp.dto.UserDto;
import dv.trunov.webapp.dto.UserInfoDto;
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
public class UserService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public void add(UserCreationDto user, String category)
            throws ResourceNotFoundException {
        UserEntity userEntity = Mapper.toUserEntity(user);
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(category);
        if (optCategory.isEmpty()) {
            throw new ResourceNotFoundException("Category not found.");
        } else {
            userEntity.setCategory(optCategory.get());
        }
        userRepository.save(userEntity);
    }

    public List<UserDto> findAll() {
        List<UserEntity> users
                = (ArrayList<UserEntity>) userRepository.findAll();
        return users.stream()
                .map(Mapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Integer id)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser = userRepository.findById(id);
        return Mapper.toUserDto(optUser.orElseThrow(
                () -> new ResourceNotFoundException("User not found.")));
    }

    public UserInfoDto findInfoById(Integer id)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser = userRepository.findById(id);
        return Mapper.toUserInfoDto(optUser.orElseThrow(
                () -> new ResourceNotFoundException("User not found.")));
    }

    public List<UserDto> findByCategory(String category) {
        return userRepository.findByCategory(category).stream()
                .map(Mapper::toUserDto)
                .collect(Collectors.toList());
    }

    public void update(Integer userId, UserCreationDto user)
            throws ResourceNotFoundException, IllegalArgumentException {
        Optional<UserEntity> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            UserEntity updatedUser = optUser.get();
            updatedUser.setFirstname(user.getFirstname());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setAddress(user.getAddress());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhone(user.getPhone());
            userRepository.save(updatedUser);
        } else {
            throw new ResourceNotFoundException("User not found.");
        }
    }

    public void updateCategory(Integer userId, String categoryName)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser
                = userRepository.findById(userId);
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(categoryName);
        if (optUser.isPresent() && optCategory.isPresent()) {
            UserEntity user = optUser.get();
            CategoryEntity category = optCategory.get();
            user.setCategory(category);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User or Category not found");
        }
    }

    public void deleteById(Integer id)
            throws ResourceNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User not found.");
        }
    }
}

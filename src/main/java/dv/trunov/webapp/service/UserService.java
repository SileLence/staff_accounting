package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.model.User;
import dv.trunov.webapp.repositories.CategoryRepository;
import dv.trunov.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public UserEntity add(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return (ArrayList<UserEntity>) userRepository.findAll();
    }

    public UserEntity findById(Integer id)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser = userRepository.findById(id);
        return optUser.orElseThrow(
                () -> new ResourceNotFoundException("User not found."));
    }

    public User findModelById(Integer id)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser = userRepository.findById(id);
        return User.toModel(optUser.orElseThrow(
                () -> new ResourceNotFoundException("User not found.")));
    }

    public List<User> findByCategory(String categoryName)
            throws ResourceNotFoundException {
        List<User> result = new ArrayList<>();
        Optional<CategoryEntity> optCategory
                = categoryRepository.findByName(categoryName);
        if (optCategory.isPresent()) {
            CategoryEntity category = optCategory.get();
            List<UserEntity> users
                    = (ArrayList<UserEntity>) userRepository.findAll();
            for (UserEntity user : users) {
                if (category.equals(user.getCategory())) {
                    result.add(User.toModel(user));
                }
            }
        } else {
            throw new ResourceNotFoundException("Category not found.");
        }
        return result;
    }

    public void update(UserEntity user)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser = userRepository.findById(user.getId());
        if (optUser.isPresent()) {
            UserEntity updatedUser = optUser.get();
            if (user.getFirstname() != null) {
                updatedUser.setFirstname(user.getFirstname());
            }
            if (user.getSurname() != null) {
                updatedUser.setSurname(user.getSurname());
            }
            if (user.getAddress() != null) {
                updatedUser.setAddress(user.getAddress());
            }
            if (user.getEmail() != null) {
                updatedUser.setEmail(user.getEmail());
            }
            if (user.getPhone() != null) {
                updatedUser.setPhone(user.getPhone());
            }
            userRepository.save(updatedUser);
        } else {
            throw new ResourceNotFoundException("User not found.");
        }
    }

    public void addCategory(Integer userId, String categoryName)
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

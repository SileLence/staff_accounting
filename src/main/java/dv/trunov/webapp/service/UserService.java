package dv.trunov.webapp.service;

import dv.trunov.webapp.domain.UserEntity;
import dv.trunov.webapp.exception.ResourceNotFoundException;
import dv.trunov.webapp.model.UserModel;
import dv.trunov.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

    public UserModel findModelById(Integer id)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser = userRepository.findById(id);
        return UserModel.toModel(optUser.orElseThrow(
                () -> new ResourceNotFoundException("User not found.")));
    }

    public UserEntity update(UserEntity user)
            throws ResourceNotFoundException {
        Optional<UserEntity> optUser = userRepository
                .findById(user.getId());
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
            return userRepository.save(updatedUser);
        } else {
            throw new ResourceNotFoundException("User not found.");
        }
    }

    public ResponseEntity<Object> deleteById(Integer id)
            throws ResourceNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User with ID:" + id + " was deleted");
        } else {
            throw new ResourceNotFoundException("User not found.");
        }
    }
}

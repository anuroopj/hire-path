package com.hirepath.hirepath_api.service;

import com.hirepath.hirepath_api.exceptions.UserNotFoundException;
import com.hirepath.hirepath_api.model.User;
import com.hirepath.hirepath_api.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException(id)
        );
    }

    public void addUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Account with same email ID exist.");
        }

        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }
}

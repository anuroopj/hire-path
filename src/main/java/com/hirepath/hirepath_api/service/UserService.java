package com.hirepath.hirepath_api.service;

import com.hirepath.hirepath_api.exceptions.UserNotFoundException;
import com.hirepath.hirepath_api.model.User;
import com.hirepath.hirepath_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }
}

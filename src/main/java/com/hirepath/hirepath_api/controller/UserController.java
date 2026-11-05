package com.hirepath.hirepath_api.controller;

import com.hirepath.hirepath_api.dto.UserRequest;
import com.hirepath.hirepath_api.dto.UserResponse;
import com.hirepath.hirepath_api.model.User;
import com.hirepath.hirepath_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> userResponses = new ArrayList<>();

        for(int i = 0; i < users.size(); i++) {
            UserResponse response = new UserResponse(
                    users.get(i).getId(),
                    users.get(i).getFirstName(),
                    users.get(i).getLastName(),
                    users.get(i).getEmail()
            );

            userResponses.add(response);
        }

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPasswordHash(userRequest.getPassword());
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        userService.removeUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

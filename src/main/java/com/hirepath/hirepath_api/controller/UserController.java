package com.hirepath.hirepath_api.controller;

import com.hirepath.hirepath_api.dto.UserRequest;
import com.hirepath.hirepath_api.dto.UserResponse;
import com.hirepath.hirepath_api.model.User;
import com.hirepath.hirepath_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.addUser(userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeUser(@PathVariable Long id) {
        User user = userService.getUserEntityById(id);
        userService.removeUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

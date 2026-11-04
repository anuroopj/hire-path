package com.hirepath.hirepath_api.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found with ID: "+ id);
    }

}

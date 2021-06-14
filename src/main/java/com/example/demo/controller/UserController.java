package com.example.demo.controller;

import com.example.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.request.ChangePasswordRequest;
import com.example.demo.payload.request.UpdateUserRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

/**
 * UserController
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            UserController
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Get all users
     *
     * @param name String
     * @return List users
     * @throws ResponseEntity            BadRequest
     * @throws ResourceNotFoundException Exception
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        return userService.findAll(name);
    }

    /**
     * Change password for user
     *
     * @param id             long
     * @param changePassword ChangePasswordRequest
     * @return Product
     * @throws ResourceNotFoundException Exception
     */
    @PostMapping("user/change_password/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> changePassword(@PathVariable("id") long id, @RequestBody ChangePasswordRequest changePassword) {
        return userService.changePassword(id, changePassword);
    }


    /**
     * Get user by id
     *
     * @param id long
     * @return Product
     * @throws ResourceNotFoundException exception
     */
    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    /**
     * Update user by id
     *
     * @param id   long
     * @param user UpdateUserRequest
     * @return Status
     * @throws ResourceNotFoundException Exception
     * @throws RuntimeException          Exception
     */
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody UpdateUserRequest user) {
        return userService.updateUser(id, user);
    }

    /**
     * Delete user by id
     *
     * @param id long
     * @return Status
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return userService.remove(id);
    }

}

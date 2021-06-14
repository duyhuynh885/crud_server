package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.request.ChangePasswordRequest;
import com.example.demo.payload.request.UpdateUserRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * UserService
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
@Service
@Transactional
public class UserService implements IUserService {


    private UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final RoleRepository roleRepository;

    /**
     * UserService
     *
     * @param userRepository UserRepository
     * @param encoder        PasswordEncoder
     * @param roleRepository RoleRepository
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    /**
     * Get all users
     *
     * @param name String
     * @return List users
     * @throws ResourceNotFoundException Exception
     */
    @Override
    public ResponseEntity<List<User>> findAll(String name) {
        try {

            List<User> lsUsers = new ArrayList<User>();
            if (name == null) {
                // check user in db
                userRepository.findAll().forEach(lsUsers::add);
                if (lsUsers.size() <= 0) {
                    throw new ResourceNotFoundException(MessageHandle.DB_NORECORD);
                }
            }
            return new ResponseEntity<>(lsUsers, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    /**
     * Get user by id
     *
     * @param id long
     * @return Product
     * @throws ResourceNotFoundException exception
     */
    @Override
    public ResponseEntity<User> findById(Long id) {
        User _user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.USER_404));
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> save(User user) {
        return null;
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

    public ResponseEntity<User> updateUser(Long id, UpdateUserRequest user) {
        User _user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.USER_404));

        // get roles from client
        Set<String> strRoles = user.getRoles();
        System.out.println(strRoles);
        Set<Role> roles = new HashSet<>();

        // set roles user if strRoles null
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {

                    case "admin":
                        // set roles admin if case = admin
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        // set roles mod if case = mod

                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        // set roles user if not found
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        try {
            // set user and save user
            _user.setRoles(roles);
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setCity(user.getCity());
            _user.setDistrict(user.getDistrict());
            _user.setPhone(user.getPhone());
            _user.setWard(user.getWard());
            _user.setAddress(user.getAddress());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete user by id
     *
     * @param id long
     * @return Status
     * @throws ResourceNotFoundException exception
     */
    @Override
    public ResponseEntity<User> remove(Long id) {
        // find user
        User p = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.USER_404));
        try {
            // delete user logic
            userRepository.deleteLogic(id, true);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Change password for user
     *
     * @param id             long
     * @param changePassword ChangePasswordRequest
     * @return Product
     * @throws ResourceNotFoundException Exception
     */
    public ResponseEntity<User> changePassword(long id, @RequestBody ChangePasswordRequest changePassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException((MessageHandle.USER_404)));
        // check old password and new password
        boolean checkOldPassword = BCrypt.checkpw(changePassword.getOldPassword(), user.getPassword());
        if (checkOldPassword) {
            // success so save password
            user.setPassword(encoder.encode(changePassword.getNewPassword()));
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

        } else {
            throw new ResourceNotFoundException("Old password incorrect!");
        }
    }

    @Override
    public ResponseEntity<?> update(Long id, User user) {
        return null;
    }
}

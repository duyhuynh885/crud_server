package com.example.demo.controller;

import com.example.demo.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.example.demo.payload.request.ForgetPasswordRequest;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;


/**
 * AuthController
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Sign In Function
     *
     * @param loginRequest LoginRequest
     * @return User
     * @throws ResponseEntity Exceiption
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    /**
     * Sign Up Function
     *
     * @param signUpRequest SignupRequest
     * @return Message
     * @throws ResponseEntity Exceiption
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }


    /**
     * Forget Password Function
     *
     * @param forgetPasswordRequest ForgetPasswordRequest
     * @return Message
     * @throws ResponseEntity Exceiption
     */
    @PostMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        return authService.forgetPassword(forgetPasswordRequest);
    }

    /**
     * Reset Password Function
     *
     * @param forgetPasswordRequest ForgetPasswordRequest
     * @return Message
     * @throws ResponseEntity Exceiption
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        return authService.resetPassword(forgetPasswordRequest);
    }


}

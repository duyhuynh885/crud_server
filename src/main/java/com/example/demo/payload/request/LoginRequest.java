package com.example.demo.payload.request;


import javax.validation.constraints.NotBlank;

/**
 * LoginRequestPayload
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    /**
     * getUsername
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setUsername
     *
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getPassword
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
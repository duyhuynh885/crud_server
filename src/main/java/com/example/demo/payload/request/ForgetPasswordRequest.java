package com.example.demo.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * ForgetPasswordRequestPayload
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class ForgetPasswordRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String token;

    /**
     * getEmail
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     *
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * getToken
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * setToken
     *
     * @param token String
     */
    public void setToken(String token) {
        this.token = token;
    }
}

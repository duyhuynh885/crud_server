package com.example.demo.payload.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * SignupRequestPayload
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
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
     * getRole
     *
     * @return username
     */
    public Set<String> getRole() {
        return this.role;
    }

    /**
     * setRole
     *
     * @param role Set
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }
}
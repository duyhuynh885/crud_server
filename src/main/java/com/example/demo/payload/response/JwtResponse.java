package com.example.demo.payload.response;

import java.util.List;

/**
 * JwtResponse
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    /**
     * @param accessToken String
     * @param id          Long
     * @param username    String
     * @param email       String
     * @param roles       List
     */
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * getAccessToken
     *
     * @return token
     */
    public String getAccessToken() {
        return token;
    }

    /**
     * setAccessToken
     *
     * @param accessToken String
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * getTokenType
     *
     * @return type
     */
    public String getTokenType() {
        return type;
    }

    /**
     * setAccessToken
     *
     * @param tokenType String
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * getId
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * setId
     *
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
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
     * getRoles
     *
     * @return roles
     */
    public List<String> getRoles() {
        return roles;
    }
}
package com.example.demo.security.services;


import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UserDetailsImpl
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * @param id          Long
     * @param username    String
     * @param email       String
     * @param password    String
     * @param authorities String
     */
    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * UserDetailsImpl
     *
     * @param user User
     * @return UserDetailsImpl
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    /**
     * getAuthorities
     *
     * @return Collection
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
     * getEmail
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * getPassword
     *
     * @return String
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * getUsername
     *
     * @return String
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * isAccountNonExpired
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isAccountNonLocked
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * isCredentialsNonExpired
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * isEnabled
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * equals
     *
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

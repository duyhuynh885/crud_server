package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import com.example.demo.model.User;

/**
 * UserRepository
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * findByUsername
     *
     * @param username String
     * @return Optional
     */
    Optional<User> findByUsername(String username);

    /**
     * existsByUsername
     *
     * @param username String
     * @return Boolean
     */
    Boolean existsByUsername(String username);

    /**
     * existsByEmail
     *
     * @param email String
     * @return Boolean
     */
    Boolean existsByEmail(String email);

    /**
     * deleteLogic
     *
     * @param id     long
     * @param status boolean
     */
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isDetele = ?2 WHERE u.id = ?1")
    void deleteLogic(long id, boolean status);

    /**
     * findByEmail
     *
     * @param email String
     * @return User
     */
    @Query("SELECT c FROM User c WHERE c.email = ?1")
    public User findByEmail(String email);

    /**
     * findByResetPasswordToken
     *
     * @param token String
     * @return User
     */

    public User findByResetPasswordToken(String token);

}
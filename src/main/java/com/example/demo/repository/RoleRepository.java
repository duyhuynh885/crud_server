package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;

/**
 * RoleRepository
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * @param name ERole
     * @return Optional
     */
    Optional<Role> findByName(ERole name);
}
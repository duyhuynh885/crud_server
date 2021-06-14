package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CartList;

/**
 * CartListRepository
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Repository
 */
@Repository
public interface CartListRepository extends JpaRepository<CartList, Long> {
    /**
     * CartList findCartListByUsername(String username);
     *
     * @param username String
     * @return CartList
     */
    CartList findCartListByUsername(String username);
}

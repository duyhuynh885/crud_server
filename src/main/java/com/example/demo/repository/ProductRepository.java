package com.example.demo.repository;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

/**
 * ProductRepository
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Repository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * deleteLogic
     *
     * @param id     long
     * @param status boolean
     */
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.isDelete= ?2,p.category=null WHERE p.id = ?1")
    void deleteLogic(long id, boolean status);

    /**
     * searchByName
     *
     * @param productName String
     * @return List
     */
    @Query("SELECT p From Product p WHERE p.productName LIKE %?1% ")
    List<Product> searchByName(String productName);

    /**
     * findByName
     *
     * @param productName String
     * @return List
     */
    @Query("SELECT p From Product p WHERE p.productName = ?1")
    List<Product> findByName(String productName);

    /**
     * findAllWithExist
     *
     * @return List
     */
    @Query("SELECT p FROM Product p WHERE p.isDelete = 0 ORDER BY p.id DESC")
    List<Product> findAllWithExist();
}

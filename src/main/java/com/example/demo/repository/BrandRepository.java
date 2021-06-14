package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.example.demo.model.Brand;
import com.example.demo.model.Category;

/**
 * BrandRepository
 * Version 1.0
 * Date: 10-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Repository
 */

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    /**
     * findByName
     *
     * @param brandName String
     * @return List
     */
    @Query("SELECT c FROM Brand c WHERE c.brandName = ?1")
    List<Brand> findByName(String brandName);

    /**
     * searchByName
     *
     * @param brandName String
     * @return List
     */
    @Query("SELECT c FROM Brand c WHERE c.brandName LIKE %?1% ")
    List<Category> searchByName(String brandName);

    /**
     * existsByBrandName
     *
     * @param name String
     * @return Boolean
     */
    Boolean existsByBrandName(String name);
}

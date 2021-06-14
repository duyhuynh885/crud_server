package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

import com.example.demo.model.Category;

/**
 * CategoryRepository
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Repository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * deleteLogic
     * @param id long
     * @param status boolean
     */
    @Transactional
    @Modifying
    @Query("UPDATE Category p SET p.isDelete= ?2 WHERE p.id = ?1")
    void deleteLogic(long id,boolean status);

    /**
     * findByName
     * @param categoryName String
     * @return List
     */
    @Query("SELECT c FROM Category c WHERE c.categoryName = ?1")
    List<Category> findByName(String categoryName);

    /**
     * searchByName
     * @param categoryName
     * @return List
     */
    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %?1% ")
    List<Category> searchByName(String categoryName);

    /**
     * existsByCategoryName
     * @param name String
     * @return Boolean
     */
    Boolean existsByCategoryName(String name);
}

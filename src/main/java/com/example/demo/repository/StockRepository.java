package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

import com.example.demo.model.Stock;

/**
 * StockRepository
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Repository
 */
public interface StockRepository extends JpaRepository<Stock, Long> {
    /**
     * findAllWithExist
     *
     * @return List
     */
    @Query("SELECT p FROM Stock p WHERE p.isDelete = 0 ORDER BY p.id DESC")
    List<Stock> findAllWithExist();
    /**
     * deleteLogic
     *
     * @param id     long
     * @param status boolean
     */
    @Transactional
    @Modifying
    @Query("UPDATE Stock p SET p.isDelete= ?2 WHERE p.id = ?1")
    void deleteLogic(long id, boolean status);

    /**
     * findByProducts_Id
     * @param idProduct long
     * @return Stock
     */
    Stock findByProducts_Id(long idProduct);
}

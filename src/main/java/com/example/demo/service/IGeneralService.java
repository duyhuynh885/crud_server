package com.example.demo.service;
import com.example.demo.payload.request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;


/**
 * IGeneralService
 * <p>
 * Version 1.0
 * <p>
 * Date: 07-06-2021
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */

public interface IGeneralService<T> {
    /**
     * findAll
     * @param name String
     * @return ResponseEntity
     */
    ResponseEntity<?> findAll(String name);

    /**
     * findById
     * @param id Long
     * @return ResponseEntity
     */
    ResponseEntity<?>  findById(Long id);

    /**
     * save
     * @param t T
     * @return ResponseEntity
     */
    ResponseEntity<?> save(T t);

    /**
     * update
     * @param id Long
     * @return ResponseEntity
     */
    ResponseEntity<?> update(Long id,T t);

    /**
     * remove
     * @param id Long
     * @return ResponseEntity
     */
    ResponseEntity<?> remove(Long id);

}
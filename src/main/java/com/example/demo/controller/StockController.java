package com.example.demo.controller;

import com.example.demo.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.model.Stock;
import com.example.demo.service.impl.StockService;
/**
 * StockController
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Get all stock
     *
     * @param name String
     * @return List stock
     */
    @GetMapping("/stocks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Stock>> getAllBrand(@RequestParam(required = false) String name) {
        return stockService.findAll(name);
    }


    /**
     * Get stock by id
     *
     * @param id long
     * @return stock
     */
    @GetMapping("/stocks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stock> getBrand(@PathVariable("id") Long id) {
        return stockService.findById(id);
    }


    /**
     * Update stock
     *
     * @param stock stock
     * @return stock
     */
    @PutMapping("/stocks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stock> updateBrand(@PathVariable("id") long id, @RequestBody Stock stock) {
        return stockService.update(id, stock);
    }
}

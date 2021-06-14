package com.example.demo.controller;

import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;

/**
 * CategoryModel
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021   DUYHV9            Create
 */


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all products
     *
     * @param name String
     * @return List products
     * @throws ResponseEntity Exception
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        return productService.findAll(name);
    }

    /**
     * Get product by id
     *
     * @param id long
     * @return Product
     * @throws ResponseEntity Exception
     */
    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Product> getProducts(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    /**
     * Create product
     *
     * @param product Product
     * @return Product
     * @throws ResponseEntity Exception
     */
    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createTutorial(@RequestBody Product product) {
        return productService.save(product);
    }

    /**
     * Update product
     *
     * @param product Product , id long
     * @return Product
     * @throws ResponseEntity Exception
     */
    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateTutorial(@PathVariable("id") long id, @RequestBody Product product) {
        return productService.update(id, product);

    }

    /**
     * Delete product by id
     *
     * @param id long
     * @return Product
     * @throws ResponseEntity Exception
     */
    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> deleteTutorial(@PathVariable("id") Long id) {
        return productService.remove(id);
    }
}

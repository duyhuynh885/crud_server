package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.impl.CategoryService;
import com.example.demo.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Category;


/**
 * CategoryController
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
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * Get all categories
     *
     * @param name String
     * @return List Category
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false) String name) {
        return categoryService.findAll(name);
    }


    /**
     * Get category by id
     *
     * @param id long
     * @return Category
     * @throws ResponseEntity Exception
     */
    @GetMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> getProducts(@PathVariable("id") long id) {
        return categoryService.findById(id);
    }

    /**
     * Find category by name
     *
     * @param categoryName String
     * @return List Category
     * @throws ResponseEntity Exception
     */
    @GetMapping("/categories/find/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Category>> findByName(@RequestParam(required = false) String categoryName) {
        return categoryService.findByName(categoryName);
    }

    /**
     * Create category
     *
     * @param category Category
     * @return Category
     * @throws ResponseEntity Exception
     */
    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    /**
     * Update category
     *
     * @param category Category
     * @return Category
     * @throws ResponseEntity Exception
     */
    @PutMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    /**
     * Delete category
     *
     * @param id long
     * @return Category
     * @throws ResponseEntity Exception
     */
    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") long id) {
        return categoryService.remove(id);
    }

}

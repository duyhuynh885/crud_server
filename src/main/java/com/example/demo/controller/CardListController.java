package com.example.demo.controller;

import com.example.demo.model.CartList;
import com.example.demo.model.Category;
import com.example.demo.service.impl.CartListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CardListController
 * Version 1.0
 * Date: 10-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 10-06-2021           DUYHV9            CardListController
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CardListController {
    private final CartListService cartListService;

    @Autowired
    public CardListController(CartListService cartListService) {
        this.cartListService = cartListService;
    }


    /**
     * Get all categories
     *
     * @param name String
     * @return List Category
     */
    @GetMapping("/cart-list")
    public ResponseEntity<List<CartList>> getAllCategory(@RequestParam(required = false) String name) {
        return cartListService.findAll(name);
    }


//    /**
//     * Get category by id
//     * @param id long
//     * @return Category
//     * @throws ResponseEntity Exception
//     */
//    @GetMapping("/categories/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Category> getProducts(@PathVariable("id") long id) {
//        return categoryService.findById(id);
//    }
//
//    /**
//     * Find category by name
//     * @param categoryName String
//     * @return List Category
//     * @throws ResponseEntity Exception
//     */
//    @GetMapping("/categories/find/")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<Category>> findByName(@RequestParam(required = false) String categoryName) {
//        return categoryService.findByName(categoryName);
//    }
//
//    /**
//     * Create category
//     * @param category Category
//     * @return Category
//     * @throws ResponseEntity Exception
//     */
//    @PostMapping("/categories")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
//        return categoryService.save(category);
//    }
//
//    /**
//     * Update category
//     * @param category Category
//     * @return Category
//     * @throws ResponseEntity Exception
//     */
//    @PutMapping("/categories/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
//        return categoryService.update(id,category);
//    }
//
//    /**
//     * Delete category
//     * @param id long
//     * @return Category
//     * @throws ResponseEntity Exception
//     */
//    @DeleteMapping("/categories/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Category> deleteCategory(@PathVariable("id") long id) {
//        return categoryService.remove(id);
//    }
}

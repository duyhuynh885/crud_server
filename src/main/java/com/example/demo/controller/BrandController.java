package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.model.Brand;
import com.example.demo.service.impl.BrandService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BrandService
 * Version 1.0
 * Date: 10-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 10-06-2021           DUYHV9            BrandService
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Get all brands
     *
     * @param name String
     * @return List Brand
     */
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrand(@RequestParam(required = false) String name) {
        return brandService.findAll(name);
    }


    /**
     * Get brands by id
     *
     * @param id long
     * @return Brand
     */
    @GetMapping("/brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Brand> getBrand(@PathVariable("id") long id) {
        return brandService.findById(id);
    }

    /**
     * Find brands by name
     *
     * @param brandName String
     * @return List Brand
     */
    @GetMapping("/brands/find/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Brand>> findByName(@RequestParam(required = false) String brandName) {
        return brandService.findByName(brandName);
    }

    /**
     * Create brands
     *
     * @param brand Brand
     * @return Brand
     */
    @PostMapping("/brands")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        return brandService.save(brand);
    }

    /**
     * Update brands
     *
     * @param brand Brand
     * @return Brand
     */
    @PutMapping("/brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") long id, @RequestBody Brand brand) {
        return brandService.update(id, brand);
    }

    /**
     * Delete brands
     *
     * @param id long
     * @return Category
     * @throws ResponseEntity Exception
     */
    @DeleteMapping("/brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Brand> deleteBrand(@PathVariable("id") long id) {
        return brandService.remove(id);
    }

}

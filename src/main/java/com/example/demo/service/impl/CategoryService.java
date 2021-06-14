package com.example.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.ICategoryService;

/**
 * CategoryService
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            CategoryService
 */

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Get all category
     *
     * @param name String
     * @return List Category
     */
    @Override
    public ResponseEntity<List<Category>> findAll(String name) {
        try {
            List<Category> lsCategories = new ArrayList<Category>();
            // check if name is null, then get all
            if (name == null)
                categoryRepository.findAll().forEach(lsCategories::add);
            // check if category no record
            if (lsCategories.isEmpty()) {
                throw new ResourceNotFoundException(MessageHandle.DB_NORECORD);
            }
            return new ResponseEntity<>(lsCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find category by id
     *
     * @param id long
     * @return Category
     */
    @Override
    public ResponseEntity<Category> findById(Long id) {
        // find category by id
        Category _category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.CATEGORY_404));
        return new ResponseEntity<>(_category, HttpStatus.OK);
    }

    /**
     * Create category to db
     *
     * @param category Category
     * @return Category
     */
    @Override
    public ResponseEntity<Category> save(Category category) {
        // Check is exist inDB
        if (categoryRepository.existsByCategoryName(category.getCategoryName().trim()))
            throw new ResourceNotFoundException(MessageHandle.CATEGORY_IS_DUPLICATE);
        try {
            // create category
            category.setDelete(false);
            category.setCategoryName(category.getCategoryName().trim());
            Category _category = categoryRepository.save(category);
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Del by id
     *
     * @param id Long
     * @return Category
     */
    @Override
    public ResponseEntity<Category> remove(Long id) {
        // check is exist in db
        Category _category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.CATEGORY_404));
        try {
            // delete category
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResourceNotFoundException(MessageHandle.DB_FOREIN_KEY_ERROR);
        }
    }

    /**
     * Search By Name
     *
     * @param categoryName String
     * @return List Category
     */
    public ResponseEntity<List<Category>> findByName(String categoryName) {
        try {
            List<Category> lsCategories = new ArrayList<Category>();
            // check is exist
            if (categoryName.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // find category by name
                categoryRepository.findByName(categoryName).forEach(lsCategories::add);
                return new ResponseEntity<>(lsCategories, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update category
     *
     * @param id       long
     * @param category Category
     * @return Category
     */
    @Override
    public ResponseEntity<Category> update(Long id, Category category) {

        Category _category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.CATEGORY_404));
        // Check is exist inDB
        if (_category.getId() != id && _category.getCategoryName().equals(category.getCategoryName()))
            throw new ResourceNotFoundException(MessageHandle.CATEGORY_IS_DUPLICATE);

        // update category
        _category.setCategoryName(category.getCategoryName());
        _category.setDelete(false);
        return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
    }

}

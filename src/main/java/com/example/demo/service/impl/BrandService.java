package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * BrandService
 * Version 1.0
 * Date: 10-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            BrandService
 */


@Service
@Transactional
public class BrandService implements IBrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    /**
     * Get all categories
     *
     * @param name String
     * @return List Category
     */
    @Override
    public ResponseEntity<List<Brand>> findAll(String name) {
        try {
            List<Brand> lsBrand = new ArrayList<Brand>();
            // check if name is null, then get all
            if (name == null)
                brandRepository.findAll().forEach(lsBrand::add);
            // check if lsBrand no record
            if (lsBrand.isEmpty()) {
                throw new ResourceNotFoundException(MessageHandle.DB_NORECORD);
            }
            return new ResponseEntity<>(lsBrand, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Search By Name
     *
     * @param brandName String
     * @return List Brand
     */
    public ResponseEntity<List<Brand>> findByName(String brandName) {
        try {
            List<Brand> lsBrands = new ArrayList<Brand>();
            // check is empty
            if (brandName.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                brandRepository.findByName(brandName).forEach(lsBrands::add);
                return new ResponseEntity<>(lsBrands, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Brand> findById(Long id) {

        Brand _brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.BRAND_404));
        return new ResponseEntity<>(_brand, HttpStatus.OK);
    }

    /**
     * create brand to db
     *
     * @param brand Brand
     * @return Brand
     */
    @Override
    public ResponseEntity<Brand> save(Brand brand) {
        // Check is exist inDB
        if (brandRepository.existsByBrandName(brand.getBrandName().trim()))
            throw new ResourceNotFoundException(MessageHandle.BRAND_IS_DUPLICATE);
        try {
            // create brand
            System.out.println(brand.getBrandName().trim());
            brand.setBrandName(brand.getBrandName().trim());
            Brand _brand = brandRepository.save(brand);
            return new ResponseEntity<>(_brand, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update brand
     *
     * @param id    long
     * @param brand Brand
     * @return Brand
     */
    @Override
    public ResponseEntity<Brand> update(Long id, Brand brand) {
        Brand _brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.BRAND_404));
        // Check is exist inDB
        if (_brand.getId() != id && _brand.getBrandName().equals(brand.getBrandName()))
            throw new ResourceNotFoundException(MessageHandle.BRAND_IS_DUPLICATE);

        // update brand
        _brand.setBrandName(brand.getBrandName());
        return new ResponseEntity<>(brandRepository.save(_brand), HttpStatus.OK);
    }

    /**
     * Del brand by id
     *
     * @param id Long
     * @return Category
     */
    @Override
    public ResponseEntity<Brand> remove(Long id) {
        // check is exist in db
        Brand _brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.BRAND_404));
        try {
            // delete logic brand
            brandRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResourceNotFoundException(MessageHandle.DB_FOREIN_KEY_ERROR);
        }
    }
}

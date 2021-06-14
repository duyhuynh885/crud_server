package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.Product;
import com.example.demo.model.Stock;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * ProductService
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */

@Service
@Transactional
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    /**
     * ProductService Constructor
     *
     * @param productRepository ProductRepository
     * @param stockRepository   StockRepository
     */
    @Autowired
    public ProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Get all Product
     *
     * @param name String
     * @return List Product
     */
    @Override
    public ResponseEntity<List<Product>> findAll(String name) {
        try {
            List<Product> lsProducts = new ArrayList<Product>();
            // if name == null get all product
            if (name == null) {
                productRepository.findAllWithExist().forEach(lsProducts::add);
                if (lsProducts.size() <= 0) {
                    throw new ResourceNotFoundException(MessageHandle.DB_NORECORD);
                }
            } else {

                productRepository.searchByName(name).forEach(lsProducts::add);
                System.out.println(lsProducts.size());
                if (lsProducts.size() <= 0) {
                    throw new ResourceNotFoundException(MessageHandle.PRODUCT_SEARCH_404);
                }
            }

            return new ResponseEntity<>(lsProducts, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    /**
     * Find product by id
     *
     * @param id long
     * @return Product
     */
    @Override
    public ResponseEntity<Product> findById(Long id) {
        // get product by id
        Product _product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.PRODUCT_404));
        return new ResponseEntity<>(_product, HttpStatus.OK);
    }

    /**
     * Create product to db
     *
     * @param product Product
     * @return Product
     */
    @Override
    public ResponseEntity<Product> save(Product product) {
        List<Product> lsProducts = new ArrayList<Product>();
        Stock _stock = new Stock();
        productRepository.findByName(product.getProductName()).forEach(lsProducts::add);
        // check duplicate Name
        for (Product p : lsProducts) {
            if (!p.isDelete() && p.getProductName().equals(product.getProductName()))
                throw new ResourceNotFoundException(MessageHandle.PRODUCT_NAME_DUP);
        }
        try {
            product.setDelete(false);
            product.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            product.setUpdateDate(new Timestamp(System.currentTimeMillis()));

            // Init stock for Product
            _stock.setStockNumber(0);
            _stock.setDelete(false);
            _stock.setSaledQuanlity(0);
            _stock.setProducts(product);
            stockRepository.save(_stock);
            Product _product = productRepository.save(product);

            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Del product by id
     *
     * @param id Long
     * @return Product
     */
    @Override
    public ResponseEntity<Product> remove(Long id) {
        // find product
        Product p = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.PRODUCT_404));
        Stock _stock = stockRepository.findByProducts_Id(id);
        try {
            // delete product logic
            _stock.setDelete(true);
            stockRepository.save(_stock);
            productRepository.deleteLogic(id, true);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update product
     *
     * @param id      long
     * @param product Product
     * @return Product
     */
    @Override
    public ResponseEntity<Product> update(Long id, Product product) {
        Product _product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.PRODUCT_404));
        List<Product> lsProducts = new ArrayList<Product>();
        productRepository.findByName(product.getProductName()).forEach(lsProducts::add);
        for (Product p : lsProducts) {
            // check duplicate name
            if (!p.isDelete() && p.getId() != id && p.getProductName().equals(product.getProductName()))
                throw new ResourceNotFoundException(MessageHandle.PRODUCT_NAME_DUP);
        }
        try {
            _product.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            _product.setProductName(product.getProductName());
            _product.setDescription(product.getDescription());
            _product.setCategory(product.getCategory());
            _product.setImage1(product.getImage1());
            _product.setPrice(product.getPrice());
            return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

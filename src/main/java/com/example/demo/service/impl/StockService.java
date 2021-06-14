package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.Product;
import com.example.demo.model.Stock;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * StockService
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
public class StockService implements IStockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * findAll stocks
     * @param name String
     * @return List
     */
    @Override
    public ResponseEntity<List<Stock>> findAll(String name) {
        try {
            List<Stock> lsStocks = new ArrayList<Stock>();
            // if name == null get all product
            if (name == null) {
                stockRepository.findAllWithExist().forEach(lsStocks::add);
                if (lsStocks.size() <= 0) {
                    throw new ResourceNotFoundException(MessageHandle.DB_NORECORD);
                }
            }
            return new ResponseEntity<>(lsStocks, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    /**
     * findById
     * @param id Long
     * @return Stock
     */
    @Override
    public ResponseEntity<Stock> findById(Long id) {
        // get product by id
        Stock _stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.STOCK_404_404));
        return new ResponseEntity<>(_stock, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Stock> save(Stock stock) {
        return null;
    }

    @Override
    public ResponseEntity<Stock> update(Long id, Stock stock) {
        Stock _stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageHandle.STOCK_404_404));
        try{
            _stock.setStockNumber(stock.getStockNumber());
            _stock.setMadeIn(stock.getMadeIn());
            _stock.setWeight(stock.getWeight());
            return new ResponseEntity<>(stockRepository.save(_stock), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> remove(Long id) {
        return null;
    }
}

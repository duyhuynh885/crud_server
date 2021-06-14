package com.example.demo.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.CartList;
import com.example.demo.service.ICartListService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.Order_Items;
import com.example.demo.repository.CartListRepository;

/**
 * CartListService
 * Version 1.0
 * Date: 10-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            CartListService
 */


@Service
@Transactional
public class CartListService implements ICartListService {
    private final CartListRepository cartListRepository;

    public CartListService(CartListRepository cartListRepository) {
        this.cartListRepository = cartListRepository;
    }


    @Override
    public ResponseEntity<List<CartList>> findAll(String name) {
        try {
            List<CartList> lsCart = new ArrayList<CartList>();
            // check if name is null, then get all
            if (name == null)
                cartListRepository.findAll().forEach(lsCart::add);
            // check if category no record
            if (lsCart.isEmpty()) {
                throw new ResourceNotFoundException(MessageHandle.DB_NORECORD);
            }
            return new ResponseEntity<>(lsCart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CartList> createCart(Long id, String username) {
        return null;
    }

    public ResponseEntity<CartList> addToCart(String username, Order_Items order_items) {
        CartList cartList = cartListRepository.findCartListByUsername(username);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CartList> findById(Long id) {
        return null;
    }


    @Override
    public ResponseEntity<CartList> save(CartList cartList) {
        return null;
    }

    @Override
    public ResponseEntity<CartList> update(Long id, CartList cartList) {
        return null;
    }

    @Override
    public ResponseEntity<CartList> remove(Long id) {
        return null;
    }
}

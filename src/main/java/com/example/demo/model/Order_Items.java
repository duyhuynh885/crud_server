package com.example.demo.model;

import javax.persistence.*;

/**
 * OrderItems
 * <p>
 * Version 1.0
 * <p>
 * Date: 07-06-2021
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            MODEL
 */
@Entity
@Table(name = "OrderItems")
public class Order_Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "totalPrice", precision = 53, scale = 0)
    private Double totalPrice;

    /**
     * @param order      Order
     * @param product    Product
     * @param quantity   Integer
     * @param totalPrice Double
     */
    public Order_Items(Order order, Product product, Integer quantity, Double totalPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    /**
     * getId
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * getOrder
     *
     * @return order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * setOrder
     *
     * @param order Order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * getProduct
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * getQuantity
     *
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * getTotalPrice
     *
     * @return totalPrice
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * setTotalPrice
     *
     * @param totalPrice Double
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

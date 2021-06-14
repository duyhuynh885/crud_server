package com.example.demo.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * CartList
 * <p>
 * Version 1.0
 * <p>
 * Date: 09-06-2021
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            MODEL
 */
@Entity
@Table(name = "cartList")
public class CartList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    @OneToMany()
    @JoinColumn(name = "order_items_id")
    private Set<Order_Items> order_items = new HashSet<Order_Items>(0);

    public CartList() {
    }

    /**
     * CartList
     *
     * @param username User
     * @param order_items Set
     */
    public CartList(String username, Set<Order_Items> order_items) {
        this.username = username;
        this.order_items = order_items;
    }


    /**
     * getId
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * setId
     *
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getUsername
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setUsername
     *
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getUser
     *
     * @return user
     */

    public Set<Order_Items> getOrder_items() {
        return order_items;
    }

    /**
     * setOrder_items
     *
     * @param order_items Set
     */
    public void setOrder_items(Set<Order_Items> order_items) {
        this.order_items = order_items;
    }
}

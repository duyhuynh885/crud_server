package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "saledQuanlity", nullable = false)
    private int saledQuanlity;

    @Column(name = "stockNumber", nullable = false)
    private int stockNumber;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "weight", nullable = true)
    private float weight;

    @Column(name = "madeIn", nullable = true)
    private String madeIn;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product products;

    @Column(name = "isDelete")
    private boolean isDelete;

    public Stock() {
    }

    /**
     * Stock
     *
     * @param saledQuanlity int
     * @param stockNumber   int
     * @param status        String
     * @param weight        float
     * @param madeIn        String
     * @param products      Product
     */
    public Stock(int saledQuanlity, int stockNumber, String status, float weight, String madeIn, Product products) {
        this.saledQuanlity = saledQuanlity;
        this.stockNumber = stockNumber;
        this.status = status;
        this.weight = weight;
        this.madeIn = madeIn;
        this.products = products;
    }

    /**
     * getSaledQuanlity
     *
     * @return saledQuanlity
     */
    public int getSaledQuanlity() {
        return saledQuanlity;
    }

    /**
     * setSaledQuanlity
     *
     * @param saledQuanlity int
     */
    public void setSaledQuanlity(int saledQuanlity) {
        this.saledQuanlity = saledQuanlity;
    }

    /**
     * getStockNumber
     *
     * @return stockNumber
     */
    public int getStockNumber() {
        return stockNumber;
    }

    /**
     * setSaledQuanlity
     *
     * @param saledQuanlity int
     */
    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    /**
     * getStatus
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * setStatus
     *
     * @param status String
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * getWeight
     *
     * @return weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * setWeight
     *
     * @param weight float
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * getMadeIn
     *
     * @return madeIn
     */
    public String getMadeIn() {
        return madeIn;
    }

    /**
     * setMadeIn
     *
     * @param madeIn String
     */
    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    /**
     * getProducts
     *
     * @return products
     */
    public Product getProducts() {
        return products;
    }

    /**
     * setProducts
     *
     * @param products Product
     */
    public void setProducts(Product products) {
        this.products = products;
    }

    /**
     * getId
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * setId
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * isDelete
     * @return boolean
     */
    public boolean isDelete() {
        return isDelete;
    }

    /**
     * setDelete
     * @param delete boolean
     */
    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}

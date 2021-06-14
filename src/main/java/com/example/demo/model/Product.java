package com.example.demo.model;


import javax.persistence.*;
import java.sql.Timestamp;


/**
 * ProductModel
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            MODEL
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "image1", nullable = false)
    private String image1;

    @Column(name = "price", nullable = false)
    private float price;


    @Column(name = "isDelete", nullable = false)
    private boolean isDelete;

    @Column(name = "createdDate", nullable = false)
    private Timestamp createdDate;

    @Column(name = "updateDate", nullable = false)
    private Timestamp updateDate;



    public Product() {
    }

    /**
     * Product
     * @param category Category
     * @param brand Brand
     * @param productName String
     * @param description String
     * @param image1 String
     * @param price  float
     * @param isDelete boolean
     * @param createdDate Timestamp
     * @param updateDate Timestamp
     */
    public Product(Category category, Brand brand, String productName, String description, String image1, float price, boolean isDelete, Timestamp createdDate, Timestamp updateDate) {
        this.category = category;
        this.brand = brand;
        this.productName = productName;
        this.description = description;
        this.image1 = image1;
        this.price = price;
        this.isDelete = isDelete;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    /**
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getCategory
     * @return Category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * setCategory
     * @param category Category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * getBrand
     * @return Brand
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * setBrand
     * @param brand Brand
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * getProductName
     * @return String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setProductName
     * @param productName String
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * getDescription
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * setDescription
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getImage1
     * @return String
     */
    public String getImage1() {
        return image1;
    }

    /**
     * setImage1
     * @param image1 String
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }

    /**
     * getPrice
     * @return float
     */
    public float getPrice() {
        return price;
    }

    /**
     * setPrice
     * @param price float
     */
    public void setPrice(float price) {
        this.price = price;
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

    /**
     * getCreatedDate
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }


    /**
     * setCreatedDate
     * @param createdDate Timestamp
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * getUpdateDate
     * @return Timestamp
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * setUpdateDate
     * @param updateDate Timestamp
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

}

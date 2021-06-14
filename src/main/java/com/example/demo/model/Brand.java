package com.example.demo.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * OrderModel
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
@Table(name = "Brand")
public class Brand implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "brandName", nullable = false)
    private String brandName;

    public Brand() {
    }

    /**
     * Brand
     *
     * @param brandName String
     */
    public Brand(String brandName) {
        this.brandName = brandName;
    }

    /**
     * getId
     *
     * @return id
     */
    public Long getId() {
        return id;
    }


    /**
     * getId
     *
     * @return id Long
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * getId
     *
     * @return brandName
     */
    public String getBrandName() {
        return brandName;
    }


    /**
     * setBrandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


}


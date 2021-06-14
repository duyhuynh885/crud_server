package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CategoryModel
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            MODEL
 */
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "isDelete", nullable = false)
    private boolean isDelete;

    public Category() {
    }

    /**
     * Category
     *
     * @param id           long
     * @param categoryName String
     * @param isDelete     boolean
     */
    public Category(long id, String categoryName, boolean isDelete) {
        this.id = id;
        this.categoryName = categoryName;
        this.isDelete = isDelete;
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
     * getCategoryName
     *
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * setCategoryName
     *
     * @param categoryName String
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * isDelete
     *
     * @return isDelete
     */
    public boolean isDelete() {
        return isDelete;
    }

    /**
     * setDelete
     *
     * @param delete boolean
     */
    public void setDelete(boolean delete) {
        isDelete = delete;
    }


}

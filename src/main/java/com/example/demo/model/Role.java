package com.example.demo.model;

import javax.persistence.*;


/**
 * RoleModel
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    /**
     * @param name ERole
     */
    public Role(ERole name) {
        this.name = name;
    }

    /**
     * getId
     *
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * setName
     *
     * @param id Integer
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getName
     *
     * @return ERole
     */
    public ERole getName() {
        return name;
    }

    /**
     * setName
     *
     * @param name ERole
     */
    public void setName(ERole name) {
        this.name = name;
    }
}
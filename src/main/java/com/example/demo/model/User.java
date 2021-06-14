package com.example.demo.model;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * UserModel
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    private Timestamp registrationDate;

    @Size(max = 11)
    private String phone;

    @Size(max = 150)
    private String city;

    @Size(max = 150)
    private String district;

    @Size(max = 150)
    private String ward;

    @Size(max = 200)
    private String address;

    @NotBlank
    private boolean isDetele;

    @NotBlank
    private boolean isBlocked;

    @NotBlank
    private long counter;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    public User() {
    }

    /**
     * User
     *
     * @param username         String
     * @param email            String
     * @param password         String
     * @param roles            Set
     * @param firstName        String
     * @param lastName         String
     * @param registrationDate Timestamp
     * @param phone            String
     * @param city             String
     * @param district         String
     * @param ward             String
     * @param address          String
     * @param isDetele         boolean
     * @param isBlocked        boolean
     * @param counter          long
     */
    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, Set<Role> roles, @Size(max = 50) String firstName, @Size(max = 50) String lastName, Timestamp registrationDate, @Size(max = 11) String phone, @Size(max = 150) String city, @Size(max = 150) String district, @Size(max = 150) String ward, @Size(max = 200) String address, @NotBlank boolean isDetele, @NotBlank boolean isBlocked, @NotBlank long counter) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.isDetele = isDetele;
        this.isBlocked = isBlocked;
        this.counter = counter;
    }

    /**
     * User
     *
     * @param username
     * @param email
     * @param password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * getFirstName
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getLastName
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setLastName
     *
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getRegistrationDate
     *
     * @return registrationDate
     */
    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    /**
     * setRegistrationDate
     *
     * @param registrationDate Timestamp
     */
    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * getPhone
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setPhone
     *
     * @param phone String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getCity
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * setCity
     *
     * @param city String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * getDistrict
     *
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * setDistrict
     *
     * @param district String
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * getWard
     *
     * @return ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * setWard
     *
     * @param ward String
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * getAddress
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setAddress
     *
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * isDetele
     *
     * @return isDetele
     */
    public boolean isDetele() {
        return isDetele;
    }

    /**
     * setDetele
     *
     * @param detele boolean
     */
    public void setDetele(boolean detele) {
        isDetele = detele;
    }

    /**
     * isBlocked
     *
     * @return isBlocked
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     * setBlocked
     *
     * @param blocked boolean
     */
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * getCounter
     *
     * @return counter
     */
    public long getCounter() {
        return counter;
    }

    /**
     * setCounter
     *
     * @param counter long
     */
    public void setCounter(long counter) {
        this.counter = counter;
    }

    /**
     * getReset_password_token
     *
     * @return resetPasswordToken
     */
    public String getReset_password_token() {
        return resetPasswordToken;
    }

    /**
     * setReset_password_token
     *
     * @param resetPasswordToken String
     */
    public void setReset_password_token(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    /**
     * getId
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
     * getEmail
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     *
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getPassword
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getRoles
     *
     * @return roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * setRoles
     *
     * @param roles Set
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
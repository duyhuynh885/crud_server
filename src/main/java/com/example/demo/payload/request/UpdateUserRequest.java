package com.example.demo.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * UpdateUserRequest
 * Version 1.0
 * Date: 07-06-2021
 * Copyright
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 07-06-2021           DUYHV9            Create
 */
public class UpdateUserRequest {

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

    private Set<String> roles = new HashSet<>();

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

    /**
     * getRoles
     *
     * @return roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * setRoles
     *
     * @param roles Set
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
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
}

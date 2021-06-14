package com.example.demo.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OrderModel
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
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "phone", nullable = false, length = 13)
    private String phone;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "ward", nullable = false)
    private String ward;

    @Column(name = "address", nullable = false)
    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "createDate", nullable = false, length = 10)
    private Date createDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "total", precision = 53, scale = 0)
    private Double total;

    @OneToMany()
    @JoinColumn(name = "order_items_id")
    private Set<Order_Items> order_items = new HashSet<Order_Items>(0);

    /**
     * Order
     *
     * @param user        User
     * @param phone       String
     * @param city        String
     * @param district    String
     * @param ward        String
     * @param address     String
     * @param createDate  Date
     * @param status      String
     * @param total       Double
     * @param order_items order_items
     */
    public Order(User user, String phone, String city, String district, String ward, String address, Date createDate, String status, Double total, Set<Order_Items> order_items) {
        this.user = user;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
        this.total = total;
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
     * getUser
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * setUser
     *
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
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
     * getCreateDate
     *
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * setCreateDate
     *
     * @param createDate Date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * getTotal
     *
     * @return total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * setTotal
     *
     * @param total Double
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * getOrder_items
     *
     * @return order_items
     */
    public Set<Order_Items> getOrder_items() {
        return order_items;
    }

    /**
     * @param order_items
     */
    public void setOrder_items(Set<Order_Items> order_items) {
        this.order_items = order_items;
    }
}

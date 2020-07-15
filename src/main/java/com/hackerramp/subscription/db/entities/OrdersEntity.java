package com.hackerramp.subscription.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name ="orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL_PRICE")
    private Float totalPrice;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

}

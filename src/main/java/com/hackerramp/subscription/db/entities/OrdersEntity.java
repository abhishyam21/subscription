package com.hackerramp.subscription.db.entities;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name ="orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL_PRICE")
    private Float totalPrice;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PAYMENT_MODE")
    private String paymentMode;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

}

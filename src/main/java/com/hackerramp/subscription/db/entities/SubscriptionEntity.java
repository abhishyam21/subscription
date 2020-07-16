package com.hackerramp.subscription.db.entities;

import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "subscription")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORDER_IDS")
    private String orderIds;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "SUBSCRIPTION_START_DATE")
    private Timestamp subscriptionStartDate;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL_PRICE")
    private Float totalPrice;

    @Column(name = "FREQUENCY")
    private Integer frequency;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NEXT_SUBSCRIPTION_DATE")
    private Timestamp nextSubscriptionDate;

    @Column(name = "SUBSCRIPTION_END_DATE")
    private Timestamp subscriptionEndDate;

    @Column(name = "IS_NOTIFICATION_PUSHED")
    private Boolean isNotificationPushed;

    @Column(name = "PAYMENT_MODE")
    private String paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUBSCRIPTION_STATUS")
    private SubscriptionStatusConstants subscriptionStatus;
}

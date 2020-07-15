package com.hackerramp.subscription.db.entities;

import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subscription")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "SUBSCRIPTION_START_DATE")
    private DateTime subscriptionStartDate;

    @Column(name = "PERIOD")
    private Integer period;

    @Column(name = "FREQUENCY")
    private Integer frequency;

    @Column(name = "NEXT_SUBSCRIPTION_DATE")
    private DateTime nextSubscriptionDate;

    @Column(name = "SUBSCRIPTION_END_DATE")
    private DateTime subscriptionEndDate;

    @Column(name = "IS_NOTIFICATION_PUSHED")
    private Boolean isNotificationPushed;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUBSCRIPTION_STATUS")
    private SubscriptionStatusConstants subscriptionStatus;
}

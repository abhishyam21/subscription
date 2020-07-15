package com.hackerramp.subscription.services.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductSubscriptionRequest {
    private String uidx;
    private String productId;
    private Long intervalInSec;
    private String address;
    private String paymentMode;
}

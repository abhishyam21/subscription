package com.hackerramp.subscription.services.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductSubscriptionResponse {
    private Long subId;
    private String uidx;
    private String productId;
    private Date orderPlacedDate;
    private Date nextOrderTriggerDate;
    private Long intervalInSec;
    private String address;
    private String paymentMode;
    private String deliveriesLeft;
}

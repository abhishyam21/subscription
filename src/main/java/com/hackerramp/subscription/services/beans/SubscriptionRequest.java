package com.hackerramp.subscription.services.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SubscriptionRequest {
    private String uidx;
    private Integer productId;
    private Integer intervalInSec;
    private String address;
    private Integer quantity;
    private Float price;
    private String paymentMode;
}

package com.hackerramp.subscription.services.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SubscriptionRequest {
    private Long subId;
    private String uidx;
    private String productId;
    private Long intervalInSec;
    private String address;
    private String paymentMode;
}

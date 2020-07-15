package com.hackerramp.subscription.services.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SubscriptionRequest {
    private Integer subId;
    private String uidx;
    private String productId;
    private Integer intervalInSec;
    private String address;
    private Integer quantity;
}

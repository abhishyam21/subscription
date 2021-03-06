package com.hackerramp.subscription.services.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderRequest {
    private String uidx;
    private Integer productId;
    private Integer quantity;
    private Float price;
    private String address;
    private String paymentMode;
}

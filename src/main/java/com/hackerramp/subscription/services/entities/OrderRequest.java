package com.hackerramp.subscription.services.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderRequest {
    private String uidx;
    private String productId;
    private Integer quantity;
    private String paymentMode;
    private Float price;
}

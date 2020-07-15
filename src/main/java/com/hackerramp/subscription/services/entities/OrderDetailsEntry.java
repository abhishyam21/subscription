package com.hackerramp.subscription.services.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDetailsEntry {
    private String uidx;
    private Long id;
    private Long productId;
    private Integer quantity;
    private Float price;
    private String paymentMode;
    private String address;
    private Date orderPlacedDate;
}

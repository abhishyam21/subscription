package com.hackerramp.subscription.services.beans;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class OrderDetailsResponse {
    private String uidx;
    private Set<OrderDetailsEntry> orderDetailsEntrySet;
}

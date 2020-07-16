package com.hackerramp.subscription.services.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDetailsEntry implements Comparable<OrderDetailsEntry>{
    private String uidx;
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Float price;
    private String address;
    private Date orderPlacedDate;
    private String paymentMode;

    @Override
    public int compareTo(OrderDetailsEntry o) {
        return o.getId().compareTo(this.id);
    }
}

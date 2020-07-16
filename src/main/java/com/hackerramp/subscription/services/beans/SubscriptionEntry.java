package com.hackerramp.subscription.services.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SubscriptionEntry {
    private Integer subId;
    private String productId;
    private DateTime orderPlacedDate;
    private DateTime nextOrderTriggerDate;
    private Integer intervalInMin;
    private String address;
    private List<Integer> orderIds;
}

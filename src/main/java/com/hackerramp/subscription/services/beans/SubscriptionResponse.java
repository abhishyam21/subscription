package com.hackerramp.subscription.services.beans;

import lombok.Data;
import lombok.ToString;
import java.util.Set;

@Data
@ToString
public class SubscriptionResponse {
    private String uidx;
    private Set<SubscriptionEntry> subscriptionEntrySet;
}

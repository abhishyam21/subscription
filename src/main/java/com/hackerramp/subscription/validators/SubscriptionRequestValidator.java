package com.hackerramp.subscription.validators;

import com.hackerramp.subscription.exception.BadInputException;
import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
public class SubscriptionRequestValidator {

    public static void validateRequest(SubscriptionRequest subscriptionRequest) throws BadInputException {
        if(isEmpty(subscriptionRequest.getUidx())){
            log.error("Empty or null userId");
            throw new BadInputException("Empty or null userId");
        }

        if(isEmpty(subscriptionRequest.getAddress())){
            subscriptionRequest.setAddress("MYNTRA ONLINE SHOPPING SITE");
        }

        if(isEmpty(subscriptionRequest.getPaymentMode())){
            subscriptionRequest.setPaymentMode("Credit Card");
        }
    }

    public static void validateRequest(SubscriptionRequest subscriptionRequest, Integer sid) throws BadInputException {
        if(sid == null){
            log.error("SID is null");
            throw new BadInputException("SID is null");
        }
        validateRequest(subscriptionRequest);
    }
}

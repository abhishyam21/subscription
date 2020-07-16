package com.hackerramp.subscription.validators;

import com.hackerramp.subscription.exception.BadInputException;
import com.hackerramp.subscription.services.beans.OrderRequest;
import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
public class OrderRequestValidator {
    public static void validateOrderRequest(OrderRequest orderRequest) throws BadInputException {
        if(isEmpty(orderRequest.getUidx())){
            log.error("Empty or null userId");
            throw new BadInputException("Empty or null userId");
        }

        if(isEmpty(orderRequest.getAddress())){
            orderRequest.setAddress("MYNTRA ONLINE SHOPPING SITE");
        }

        if(isEmpty(orderRequest.getPaymentMode())){
            orderRequest.setPaymentMode("Credit Card");
        }
    }
}

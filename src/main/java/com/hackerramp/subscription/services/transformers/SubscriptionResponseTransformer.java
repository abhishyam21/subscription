package com.hackerramp.subscription.services.transformers;

import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import com.hackerramp.subscription.services.beans.SubscriptionEntry;
import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import com.hackerramp.subscription.services.beans.SubscriptionResponse;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class SubscriptionResponseTransformer {

    public static SubscriptionResponse transformEntitiesToResponse(List<SubscriptionEntity> subscriptionEntities, String uidx){
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.setUidx(uidx);
        Set<SubscriptionEntry> subscriptionEntrySet = subscriptionEntities.stream()
                .map(SubscriptionResponseTransformer::transformEntityToEntry)
                .collect(Collectors.toSet());
        subscriptionResponse.setSubscriptionEntrySet(subscriptionEntrySet);
        return subscriptionResponse;
    }

    public static SubscriptionResponse transformEntityToResponse(SubscriptionEntity subscriptionEntity){
        SubscriptionResponse response = new SubscriptionResponse();
        response.setSubscriptionEntrySet(Collections.singleton(transformEntityToEntry(subscriptionEntity)));
        response.setUidx(subscriptionEntity.getUserId());
        return response;
    }

    public static SubscriptionEntry transformEntityToEntry(SubscriptionEntity subscriptionEntity){
        SubscriptionEntry subscriptionEntry = new SubscriptionEntry();
        subscriptionEntry.setIntervalInMin(subscriptionEntity.getFrequency());
        subscriptionEntry.setSubId(subscriptionEntity.getId());
        subscriptionEntry.setAddress(subscriptionEntity.getAddress());
        subscriptionEntry.setNextOrderTriggerDate(subscriptionEntity.getNextSubscriptionDate());
        subscriptionEntry.setProductId(subscriptionEntity.getProductId());
        subscriptionEntry.setOrderPlacedDate(subscriptionEntity.getSubscriptionStartDate());
        subscriptionEntry.setOrderIds(stringToList(subscriptionEntity.getOrderIds()));
        return subscriptionEntry;
    }

    public static SubscriptionEntity transformRequestToEntity(SubscriptionRequest subscriptionRequest){
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setUserId(subscriptionRequest.getUidx());
        subscriptionEntity.setProductId(subscriptionRequest.getProductId());
        DateTime dateTime = new DateTime();
        subscriptionEntity.setSubscriptionStartDate(new Timestamp(dateTime.getMillis()));
        subscriptionEntity.setFrequency(subscriptionRequest.getIntervalInSec());
        subscriptionEntity.setAddress(subscriptionRequest.getAddress());
        subscriptionEntity.setQuantity(subscriptionRequest.getQuantity());
        subscriptionEntity.setNextSubscriptionDate(calculateNextTime(dateTime, subscriptionRequest.getIntervalInSec()));
        subscriptionEntity.setIsNotificationPushed(false);
        subscriptionEntity.setPaymentMode(subscriptionRequest.getPaymentMode());
        subscriptionEntity.setTotalPrice(subscriptionRequest.getPrice());
        subscriptionEntity.setSubscriptionStatus(SubscriptionStatusConstants.ACTIVE);
        return subscriptionEntity;
    }

    public static Timestamp calculateNextTime(DateTime dateTime, Integer intervalInSec) {
        return new Timestamp(dateTime.plusSeconds(intervalInSec).getMillis());
    }

    private static List<Integer> stringToList(String listInString){
        return Arrays
                .stream(listInString.split(";"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static void transformRequestToEntity(SubscriptionRequest request, SubscriptionEntity subscriptionEntity) {
        if(isNotEmpty(request.getUidx())){
            subscriptionEntity.setUserId(request.getUidx());
        }
        if(request.getProductId() == null){
            subscriptionEntity.setProductId(request.getProductId());
        }
        if(isNotEmpty(request.getAddress())){
            subscriptionEntity.setAddress(request.getAddress());
        }
        if(isNotEmpty(request.getPaymentMode())){
            subscriptionEntity.setPaymentMode(request.getPaymentMode());
        }
        if(request.getIntervalInSec() != null){
            subscriptionEntity.setFrequency(request.getIntervalInSec());
            subscriptionEntity.setNextSubscriptionDate(calculateNextTime(new DateTime(), request.getIntervalInSec()));
        }
        if(request.getQuantity() != null){
            subscriptionEntity.setQuantity(request.getQuantity());
        }
        if(request.getPrice() != null){
            subscriptionEntity.setTotalPrice(request.getPrice());
        }
    }
}

package com.hackerramp.subscription.services.transformers;

import antlr.StringUtils;
import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import com.hackerramp.subscription.services.beans.SubscriptionEntry;
import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import com.hackerramp.subscription.services.beans.SubscriptionResponse;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionResponseTransformer {

    public static SubscriptionResponse transformEntitiesToResponse(List<SubscriptionEntity> subscriptionEntities, String uidx){
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.setUidx(uidx);
        for( SubscriptionEntity subscriptionEntity : subscriptionEntities){
            subscriptionResponse.getSubscriptionEntrySet().add(transformEntityToEntry(subscriptionEntity));
        }
        return subscriptionResponse;
    }

    public static SubscriptionResponse transformEntityToResponse(SubscriptionEntity subscriptionEntity){
        SubscriptionResponse subscriptionResponse = new SubscriptionResponse();
        subscriptionResponse.getSubscriptionEntrySet().add(transformEntityToEntry(subscriptionEntity));
        subscriptionResponse.setUidx(subscriptionEntity.getUserId());
        return subscriptionResponse;
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
        subscriptionEntity.setFrequency(subscriptionRequest.getIntervalInSec());
        subscriptionEntity.setAddress(subscriptionRequest.getAddress());
        subscriptionEntity.setQuantity(subscriptionRequest.getQuantity());
        subscriptionEntity.setSubscriptionStartDate(new DateTime());
        subscriptionEntity.setSubscriptionStatus(SubscriptionStatusConstants.ACTIVE);
        return subscriptionEntity;
    }

    private static List<Integer> stringToList(String listInString){
        List<Integer> objectList = new ArrayList<>();
        String[] orderStrings = listInString.split(";");
        for(int i=0; i<orderStrings.length; i++){
          objectList.add(Integer.parseInt(orderStrings[i]));
        }
        return objectList;
    }
}

package com.hackerramp.subscription.services;

import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import com.hackerramp.subscription.services.beans.SubscriptionResponse;
import com.hackerramp.subscription.services.transformers.SubscriptionResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    public SubscriptionResponse getByUserId(String uidx){
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepo.findByUserIdAndSubscriptionStatus(uidx, SubscriptionStatusConstants.ACTIVE);
        return SubscriptionResponseTransformer.transformEntitiesToResponse(subscriptionEntityList, uidx);
    }

    public SubscriptionResponse getBySubscriptionId(Integer sid){
        Optional<SubscriptionEntity> subscriptionEntity = subscriptionRepo.findById(sid);
        return subscriptionEntity.map(SubscriptionResponseTransformer::transformEntityToResponse).orElse(null);
    }

    public void subscribe(SubscriptionRequest subscriptionRequest){
        SubscriptionEntity subscriptionEntity = SubscriptionResponseTransformer.transformRequestToEntity(subscriptionRequest);
        subscriptionRepo.save(subscriptionEntity);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void unsubscribe(Integer sid){
        Optional<SubscriptionEntity> subscriptionEntity = subscriptionRepo.findById(sid);
        subscriptionEntity.get().setSubscriptionStatus(SubscriptionStatusConstants.CANCELLED);
        subscriptionRepo.save(subscriptionEntity.get());
    }
}

package com.hackerramp.subscription.services;

import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import com.hackerramp.subscription.exception.BadInputException;
import com.hackerramp.subscription.services.beans.OrderDetailsEntry;
import com.hackerramp.subscription.services.beans.OrderDetailsResponse;
import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import com.hackerramp.subscription.services.beans.SubscriptionResponse;
import com.hackerramp.subscription.services.transformers.SubscriptionResponseTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.hackerramp.subscription.constants.SubscriptionStatusConstants.*;
import static com.hackerramp.subscription.services.transformers.OrderResponseTransformer.transformSubscriptionEntityToDBEntity;
import static com.hackerramp.subscription.services.transformers.SubscriptionResponseTransformer.transformEntityToResponse;
import static com.hackerramp.subscription.services.transformers.SubscriptionResponseTransformer.transformRequestToEntity;

@Slf4j
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private OrderService orderService;

    public SubscriptionResponse getByUserId(String uidx){
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepo.findByUserIdAndSubscriptionStatus(uidx, ACTIVE);
        return SubscriptionResponseTransformer.transformEntitiesToResponse(subscriptionEntityList, uidx);
    }

    public SubscriptionResponse getBySubscriptionId(Integer sid){
        Optional<SubscriptionEntity> subscriptionEntity = subscriptionRepo.findById(sid);
        return subscriptionEntity.map(SubscriptionResponseTransformer::transformEntityToResponse).orElse(null);
    }

    public SubscriptionResponse subscribe(SubscriptionRequest subscriptionRequest) throws BadInputException {
        SubscriptionEntity subscriptionEntity = transformRequestToEntity(subscriptionRequest);
        createOrder(subscriptionEntity);
        subscriptionEntity = subscriptionRepo.save(subscriptionEntity);
        return transformEntityToResponse(subscriptionEntity);
    }

    public SubscriptionEntity createOrder(SubscriptionEntity subscriptionEntity) throws BadInputException {
        OrderDetailsResponse orderDetailsResponse = orderService.createOrder(transformSubscriptionEntityToDBEntity(subscriptionEntity));
        setOrderId(orderDetailsResponse.getOrderDetailsEntrySet(), subscriptionEntity);
        return subscriptionEntity;
    }

    private void setOrderId(Set<OrderDetailsEntry> orderDetailsEntrySet, SubscriptionEntity subscriptionEntity) {
        Optional<Integer> orderIdOptional = orderDetailsEntrySet.stream().map(OrderDetailsEntry::getId).findFirst();
        String orderIds = subscriptionEntity.getOrderIds();
        if(StringUtils.isEmpty(orderIds)){
            orderIds = String.valueOf(orderIdOptional.get());
        }else {
            orderIds+=";"+ orderIdOptional.get();
        }
        subscriptionEntity.setOrderIds(orderIds);
    }

    public SubscriptionResponse editSubscription(SubscriptionRequest subscriptionRequest, Integer sid) throws BadInputException {
        Optional<SubscriptionEntity> subscriptionEntityOptional = subscriptionRepo.findById(sid);
        if(subscriptionEntityOptional.isPresent()){
            SubscriptionEntity subscriptionEntity = subscriptionEntityOptional.get();
            SubscriptionResponseTransformer.transformRequestToEntity(subscriptionRequest, subscriptionEntity);
            subscriptionEntity = subscriptionRepo.save(subscriptionEntity);
            return transformEntityToResponse(subscriptionEntity);
        }else {
            log.error("No Subscription Entity found with Id {}", sid);
            throw new BadInputException("No Subscription Entity found with Id "+ sid);
        }
    }


    public String unsubscribe(Integer sid) throws BadInputException {
        Optional<SubscriptionEntity> entity = subscriptionRepo.findById(sid);
        if(entity.isPresent()) {
            SubscriptionEntity subscriptionEntity = entity.get();
            if(CANCELLED.equals(subscriptionEntity.getSubscriptionStatus())){
                return "Already Un-subscribed";
            }
            subscriptionEntity.setSubscriptionStatus(CANCELLED);
            subscriptionEntity.setSubscriptionEndDate(new Timestamp(new DateTime().getMillis()));
            subscriptionRepo.save(subscriptionEntity);
            return "Un-Subscribed";
        }else {
            log.error("No Subscription Entity found with Id {}", sid);
            throw new BadInputException("No Subscription Entity found with Id "+ sid);
        }
    }
}

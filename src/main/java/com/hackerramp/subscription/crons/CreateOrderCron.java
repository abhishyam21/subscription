package com.hackerramp.subscription.crons;

import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hackerramp.subscription.constants.FilterConstants.*;

@Slf4j
@Component
public class CreateOrderCron {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Scheduled(fixedRate = 5000)
    public void scheduleCreateOrders(){
        log.info("Running CreateOrderCron cron JOB");

        List<SubscriptionEntity> subscriptionEntityList = (List<SubscriptionEntity>)subscriptionRepo.findAll();
        Set<SubscriptionEntity> filteredSubscriptionEntitySet = subscriptionEntityList
                .stream()
                .filter(filterIfSameDay
                        .and(filterSubscriptionIsActive)
                        .and(filterIsNotificationAlreadyPushed)
                        .negate()
                )
                .collect(Collectors.toSet());
        createOrders(filteredSubscriptionEntitySet);
    }

    private void createOrders(Set<SubscriptionEntity> subscriptionEntitySet) {
        subscriptionEntitySet.forEach(subscriptionEntity ->
                log.info("Creating order for subscription: {}",subscriptionEntity));
    }

}

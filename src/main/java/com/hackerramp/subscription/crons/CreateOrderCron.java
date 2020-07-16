package com.hackerramp.subscription.crons;

import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import com.hackerramp.subscription.exception.BadInputException;
import com.hackerramp.subscription.services.NotificationService;
import com.hackerramp.subscription.services.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hackerramp.subscription.constants.FilterConstants.*;
import static com.hackerramp.subscription.constants.SubscriptionStatusConstants.*;
import static com.hackerramp.subscription.services.transformers.SubscriptionResponseTransformer.calculateNextTime;

@Slf4j
@Component
public class CreateOrderCron {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 5000)
    public void scheduleCreateOrders(){
        log.info("Running CreateOrderCron cron JOB");

        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepo.findBySubscriptionStatus(ACTIVE);
        Set<SubscriptionEntity> filteredSubscriptionEntitySet = subscriptionEntityList
                .stream()
                .filter(
                        filterIfSameDay
                        .and(filterSubscriptionIsActive)
                        .and(filterIsNotificationAlreadyPushed)
                )
                .collect(Collectors.toSet());
        createOrders(filteredSubscriptionEntitySet);
    }

    private void createOrders(Set<SubscriptionEntity> subscriptionEntitySet) {
        subscriptionEntitySet.forEach(subscriptionEntity -> {
            log.info("Creating order for subscription: {}", subscriptionEntity);
                    try {
                        SubscriptionEntity entity = subscriptionService.createOrder(subscriptionEntity);
                        entity.setNextSubscriptionDate(calculateNextTime(new DateTime(), entity.getFrequency()));
                        subscriptionRepo.save(entity);
                        notificationService.sendNotification("","You Subscription order is successfully placed");
                    } catch (BadInputException e) {
                        log.error("Error while creating order for subscriptionId: {}, {}",
                                subscriptionEntity.getId(),
                                e);
                        e.printStackTrace();
                    } catch (IOException e) {
                        log.error("Error while sending notification to user:",e);
                    }
                }
        );
    }

}

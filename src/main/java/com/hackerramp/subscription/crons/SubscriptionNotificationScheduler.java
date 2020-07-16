package com.hackerramp.subscription.crons;

import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import com.hackerramp.subscription.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hackerramp.subscription.constants.FilterConstants.*;
import static com.hackerramp.subscription.constants.SubscriptionStatusConstants.ACTIVE;

@Slf4j
@Component
public class SubscriptionNotificationScheduler {

    @Autowired
    private SubscriptionRepo subscriptionRepo;
    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 8000)
    public void scheduleNotifications() {
        log.info("Running SubscriptionNotificationScheduler cron JOB");

        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepo.findBySubscriptionStatus(ACTIVE);
        Set<SubscriptionEntity> filteredSubscriptionEntitySet = subscriptionEntityList
                .stream()
                .filter(filterIfDaysDifferenceIsLessThanTwo
                        .and(filterSubscriptionIsActive)
                        .and(filterIsNotificationNoyYetPushed)
                )
                .collect(Collectors.toSet());
        sendNotifications(filteredSubscriptionEntitySet);
    }

    private void sendNotifications(Set<SubscriptionEntity> subscriptionEntitySet) {
        subscriptionEntitySet.forEach(subscriptionEntity -> {
            log.info("Sending Notification {}", subscriptionEntity);
            try {
                notificationService.sendNotification("", "Your product subscription renewal confirmation",
                        subscriptionEntity.getProductId());
            } catch (IOException e) {
                log.error("Error while sending notification to user:",e);
            }
            subscriptionEntity.setIsNotificationPushed(true);
            subscriptionRepo.save(subscriptionEntity);
        });

    }
}

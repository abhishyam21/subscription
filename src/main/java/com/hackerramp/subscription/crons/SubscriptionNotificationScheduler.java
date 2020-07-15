package com.hackerramp.subscription.crons;

import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SubscriptionNotificationScheduler {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    private final Predicate<SubscriptionEntity> filterIfDaysDifferenceIsLessThanTwo = subscriptionEntity -> {
        DateTime afterTwoDaysDate = new DateTime();
        DateTime nextSubscriptionDate = subscriptionEntity.getNextSubscriptionDate();
        Days differenceInDays = Days.daysBetween(afterTwoDaysDate, nextSubscriptionDate);
        return differenceInDays.getDays() <= 2;
    };

    private final Predicate<SubscriptionEntity> filterSubscriptionIsActive = subscriptionEntity ->
            subscriptionEntity.getSubscriptionStatus().equals(SubscriptionStatusConstants.ACTIVE);

    private final Predicate<SubscriptionEntity> filterIsNotificationAlreadyPushed = subscriptionEntity ->
            !subscriptionEntity.getIsNotificationPushed();


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("Running SubscriptionNotificationScheduler cron JOB");

        List<SubscriptionEntity> subscriptionEntityList = (List<SubscriptionEntity>)subscriptionRepo.findAll();
        Set<SubscriptionEntity> filteredSubscriptionEntitySet = subscriptionEntityList
                .stream()
                .filter(filterIfDaysDifferenceIsLessThanTwo
                        .and(filterSubscriptionIsActive)
                        .and(filterIsNotificationAlreadyPushed)
                )
                .collect(Collectors.toSet());
        sendNotifications(filteredSubscriptionEntitySet);
    }

    private void sendNotifications(Set<SubscriptionEntity> subscriptionEntitySet) {
        subscriptionEntitySet.forEach(subscriptionEntity -> log.info("Sending Notification {}", subscriptionEntity));
    }
}

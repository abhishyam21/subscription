package com.hackerramp.subscription.constants;

import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import org.joda.time.DateTime;

import java.util.function.Predicate;

public interface FilterConstants {
     Predicate<SubscriptionEntity> filterIfDaysDifferenceIsLessThanTwo = subscriptionEntity -> {
         DateTime now = new DateTime();
         long diffInMilliSec = subscriptionEntity.getNextSubscriptionDate().getTime()- now.getMillis();
         return 60000 >= diffInMilliSec; //half minute difference
     };

     Predicate<SubscriptionEntity> filterSubscriptionIsActive = subscriptionEntity ->
            subscriptionEntity.getSubscriptionStatus().equals(SubscriptionStatusConstants.ACTIVE);

     Predicate<SubscriptionEntity> filterIsNotificationNoyYetPushed = subscriptionEntity ->
            !subscriptionEntity.getIsNotificationPushed();

    Predicate<SubscriptionEntity> filterIsNotificationAlreadyPushed = SubscriptionEntity::getIsNotificationPushed;

    Predicate<SubscriptionEntity> filterIfSameDay = subscriptionEntity -> {
        DateTime now = new DateTime();
        long diffInMilliSec = subscriptionEntity.getNextSubscriptionDate().getTime()- now.getMillis();
        return 10000 >= diffInMilliSec; //half minute difference
    };
}

package com.hackerramp.subscription.constants;

import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.function.Predicate;

public interface FilterConstants {
     Predicate<SubscriptionEntity> filterIfDaysDifferenceIsLessThanTwo = subscriptionEntity -> {
        DateTime afterTwoDaysDate = new DateTime();
        DateTime nextSubscriptionDate = subscriptionEntity.getNextSubscriptionDate();
        Days differenceInDays = Days.daysBetween(afterTwoDaysDate, nextSubscriptionDate);
        return differenceInDays.getDays() <= 2;
    };

     Predicate<SubscriptionEntity> filterSubscriptionIsActive = subscriptionEntity ->
            subscriptionEntity.getSubscriptionStatus().equals(SubscriptionStatusConstants.ACTIVE);

     Predicate<SubscriptionEntity> filterIsNotificationAlreadyPushed = subscriptionEntity ->
            !subscriptionEntity.getIsNotificationPushed();

    Predicate<SubscriptionEntity> filterIfSameDay = subscriptionEntity -> {
        DateTime afterTwoDaysDate = new DateTime();
        DateTime nextSubscriptionDate = subscriptionEntity.getNextSubscriptionDate();
        Days differenceInDays = Days.daysBetween(afterTwoDaysDate, nextSubscriptionDate);
        return differenceInDays.getDays() <= 0;
    };
}

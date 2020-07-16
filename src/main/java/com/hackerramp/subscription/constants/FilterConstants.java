package com.hackerramp.subscription.constants;

import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.function.Predicate;

public interface FilterConstants {
     Predicate<SubscriptionEntity> filterIfDaysDifferenceIsLessThanTwo = subscriptionEntity -> {
        DateTime afterTwoDaysDate = new DateTime();
        DateTime nextSubscriptionDate = new DateTime(subscriptionEntity.getNextSubscriptionDate());
        Days differenceInDays = Days.daysBetween(afterTwoDaysDate, nextSubscriptionDate);
        return differenceInDays.getDays() <= 2;
    };

     Predicate<SubscriptionEntity> filterSubscriptionIsActive = subscriptionEntity ->
            subscriptionEntity.getSubscriptionStatus().equals(SubscriptionStatusConstants.ACTIVE);

     Predicate<SubscriptionEntity> filterIsNotificationNoyYetPushed = subscriptionEntity ->
            !subscriptionEntity.getIsNotificationPushed();

    Predicate<SubscriptionEntity> filterIsNotificationAlreadyPushed = SubscriptionEntity::getIsNotificationPushed;

    Predicate<SubscriptionEntity> filterIfSameDay = subscriptionEntity -> {
        DateTime afterTwoMinutes = new DateTime();
        long diffInMilliSec = subscriptionEntity.getNextSubscriptionDate().getTime()- afterTwoMinutes.getMillis();
        return 30000 >= diffInMilliSec; //half minute difference
    };
}

package com.hackerramp.subscription.db;

import com.hackerramp.subscription.constants.SubscriptionStatusConstants;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends CrudRepository<SubscriptionEntity, Integer> {

    List<SubscriptionEntity> findByUserIdAndSubscriptionStatus(String userId, SubscriptionStatusConstants status);

    List<SubscriptionEntity> findBySubscriptionStatus(SubscriptionStatusConstants status);
}

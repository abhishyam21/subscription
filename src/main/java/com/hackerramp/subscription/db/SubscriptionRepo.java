package com.hackerramp.subscription.db;

import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends CrudRepository<SubscriptionEntity, Integer> {
}

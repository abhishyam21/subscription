package com.hackerramp.subscription.db;

import com.hackerramp.subscription.db.entities.OrdersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends CrudRepository<OrdersEntity, String> {

    OrdersEntity findByUserId(String userId);
}

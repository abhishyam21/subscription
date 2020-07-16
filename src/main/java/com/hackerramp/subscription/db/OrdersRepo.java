package com.hackerramp.subscription.db;

import com.hackerramp.subscription.db.entities.OrdersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends CrudRepository<OrdersEntity, Integer> {

    List<OrdersEntity> findByUserId(String userId);
}

package com.hackerramp.subscription.db;

import com.hackerramp.subscription.db.entities.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends CrudRepository<Orders, String> {
}

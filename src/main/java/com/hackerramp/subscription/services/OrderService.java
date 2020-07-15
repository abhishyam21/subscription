package com.hackerramp.subscription.services;

import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.entities.OrdersEntity;
import com.hackerramp.subscription.services.beans.OrderDetailsResponse;
import com.hackerramp.subscription.services.transformers.OrderResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    public List<OrdersEntity> getAllOrders() {
        return (List<OrdersEntity>) ordersRepo.findAll();
    }

    public OrderDetailsResponse getOrder(String uidx) {
        OrdersEntity ordersEntity = ordersRepo.findByUserId(uidx);
        return OrderResponseTransformer.transformOrderEntityToResponseObject(ordersEntity);
    }
}

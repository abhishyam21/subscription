package com.hackerramp.subscription.services.transformers;


import com.hackerramp.subscription.db.entities.OrdersEntity;
import com.hackerramp.subscription.services.beans.OrderDetailsResponse;

public class OrderResponseTransformer {

    public static OrderDetailsResponse transformOrderEntityToResponseObject(OrdersEntity ordersEntity) {
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
        return orderDetailsResponse;
    }
}

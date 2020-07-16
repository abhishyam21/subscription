package com.hackerramp.subscription.services.transformers;


import com.hackerramp.subscription.db.entities.OrdersEntity;
import com.hackerramp.subscription.services.beans.OrderDetailsEntry;
import com.hackerramp.subscription.services.beans.OrderDetailsResponse;
import com.hackerramp.subscription.services.beans.OrderRequest;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderResponseTransformer {

    public static OrderDetailsResponse transformOrderEntityToResponseObject(OrdersEntity ordersEntity) {
        return transformOrderEntityToResponseObject(Collections.singletonList(ordersEntity));
    }

    public static OrderDetailsResponse transformOrderEntityToResponseObject(List<OrdersEntity> ordersEntity) {
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
        if(!CollectionUtils.isEmpty(ordersEntity)) {
            orderDetailsResponse.setUidx(ordersEntity.get(0).getUserId());
            Set<OrderDetailsEntry> orderDetailsEntryList = ordersEntity.stream()
                    .map(OrderResponseTransformer::transformEntity)
                    .sorted()
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            orderDetailsResponse.setOrderDetailsEntrySet(orderDetailsEntryList);
        }
        return orderDetailsResponse;
    }

    private static OrderDetailsEntry transformEntity(OrdersEntity entity) {
        OrderDetailsEntry orderDetailsEntry = new OrderDetailsEntry();
        orderDetailsEntry.setUidx(entity.getUserId());
        orderDetailsEntry.setId(entity.getId());
        orderDetailsEntry.setProductId(entity.getProductId());
        orderDetailsEntry.setQuantity(entity.getQuantity());
        orderDetailsEntry.setPrice(entity.getTotalPrice());
        orderDetailsEntry.setAddress(entity.getAddress());
        orderDetailsEntry.setPaymentMode(entity.getPaymentMode());
        orderDetailsEntry.setOrderPlacedDate(entity.getCreatedDate());

        return orderDetailsEntry;
    }

    public static OrdersEntity transformOrderRequestToDBEntity(OrderRequest request){
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setProductId(request.getProductId());
        ordersEntity.setUserId(request.getUidx());
        ordersEntity.setQuantity(request.getQuantity());
        ordersEntity.setTotalPrice(request.getPrice());
        ordersEntity.setAddress(request.getAddress());
        ordersEntity.setPaymentMode(request.getPaymentMode());
        ordersEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return ordersEntity;
    }
}

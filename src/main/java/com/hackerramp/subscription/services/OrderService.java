package com.hackerramp.subscription.services;

import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.entities.OrdersEntity;
import com.hackerramp.subscription.exception.BadInputException;
import com.hackerramp.subscription.services.beans.OrderDetailsResponse;
import com.hackerramp.subscription.services.beans.OrderRequest;
import com.hackerramp.subscription.services.transformers.OrderResponseTransformer;
import com.hackerramp.subscription.validators.OrderRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.hackerramp.subscription.services.transformers.OrderResponseTransformer.transformOrderEntityToResponseObject;
import static com.hackerramp.subscription.services.transformers.OrderResponseTransformer.transformOrderRequestToDBEntity;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private NotificationService notificationService;

    public List<OrdersEntity> getAllOrders() {
        return (List<OrdersEntity>) ordersRepo.findAll();
    }

    public OrderDetailsResponse getOrder(String uidx) {
        List<OrdersEntity> ordersEntity = ordersRepo.findByUserId(uidx);
        return transformOrderEntityToResponseObject(ordersEntity);
    }

    public OrderDetailsResponse getOrdersById(Integer orderId) {
        Optional<OrdersEntity> ordersEntity = ordersRepo.findById(orderId);
       return ordersEntity
               .map(OrderResponseTransformer::transformOrderEntityToResponseObject)
               .orElse(null);
    };

    public OrderDetailsResponse createOrder(OrderRequest orderRequest) throws BadInputException {
        OrderRequestValidator.validateOrderRequest(orderRequest);
        OrdersEntity ordersEntity = transformOrderRequestToDBEntity(orderRequest);
        ordersEntity = ordersRepo.save(ordersEntity);
        try {
            notificationService.sendNotification("","Thank you for shopping with us!!\\n You order is successfully placed");
        } catch (IOException e) {
            log.error("Error while sending notification to user:",e);
        }
        return transformOrderEntityToResponseObject(ordersEntity);
    }
}

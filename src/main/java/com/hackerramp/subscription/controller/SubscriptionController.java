package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.OrdersEntity;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("subscription")
public class SubscriptionController {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @GetMapping("/showAllOrders")
    public ResponseEntity<List<OrdersEntity>> getOrders(){
        log.info("In Get All orders API");
        return ResponseEntity.ok().body((List<OrdersEntity>) ordersRepo.findAll());
    }

    @GetMapping("/showAllSubscriptions")
    public ResponseEntity<List<SubscriptionEntity>> getSubscriptions(){
        return ResponseEntity.ok().body((List<SubscriptionEntity>) subscriptionRepo.findAll());
    }

}

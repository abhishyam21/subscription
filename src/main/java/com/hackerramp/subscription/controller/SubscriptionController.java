package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("subscription")
public class SubscriptionController {

    @Autowired
    private OrdersRepo ordersRepo;

    @GetMapping("/showAllOrders")
    public ResponseEntity<List<Orders>> getEmployees(){

        return ResponseEntity.ok().body((List<Orders>) ordersRepo.findAll());
    }
}

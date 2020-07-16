package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.exception.BadInputException;
import com.hackerramp.subscription.services.OrderService;
import com.hackerramp.subscription.services.beans.OrderDetailsResponse;
import com.hackerramp.subscription.services.beans.OrderRequest;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.entities.OrdersEntity;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/showAllOrders")
    public ResponseEntity<List<OrdersEntity>> getOrders(){
        log.info("In ShowALLOrders API");
        List<OrdersEntity> ordersEntityList = orderService.getAllOrders();
        return ResponseEntity.ok().body(ordersEntityList);
    }

    @GetMapping(value = {"/users/{uidx}"})
    private ResponseEntity<OrderDetailsResponse> getOrdersByUidx(@PathVariable("uidx") @NotNull String uidx) {
        log.info("In /users/{uidx} API");
        OrderDetailsResponse orderDetailsResponse = orderService.getOrder(uidx);
        return ResponseEntity.ok().body(orderDetailsResponse);
    }

    @GetMapping(value = {"/orders/{orderId}"})
    private ResponseEntity<OrderDetailsResponse> getByOrderId(@PathVariable("orderId") @NotNull Integer orderId) {
        log.info("In /orders/{orderId} API");
        OrderDetailsResponse orderDetailsResponse = orderService.getOrdersById(orderId);
        if(orderDetailsResponse == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(orderDetailsResponse);
    }

    @PostMapping(value = {"/place"})
    private ResponseEntity<OrderDetailsResponse> subscribeToProduct(@RequestBody OrderRequest orderRequest){
        log.info("In create order flow API");
        try {
            OrderDetailsResponse orderDetailsResponse = orderService.createOrder(orderRequest);
            return ResponseEntity.created(URI.create("/place")).body(orderDetailsResponse);
        } catch (BadInputException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.beans.OrderDetailsResponse;
import com.hackerramp.subscription.services.beans.OrderRequest;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.entities.OrdersEntity;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersRepo ordersRepo;

    @GetMapping("/showAllOrders")
    public ResponseEntity<List<OrdersEntity>> getOrders(){
        return ResponseEntity.ok().body((List<OrdersEntity>) ordersRepo.findAll());
    }

    @GetMapping(value = {"/users/{uidx}"})
    private OrderDetailsResponse getOrdersByUidx(@PathVariable("uidx") @NotNull String uidx) throws Exception{
        return null;
    }

    @GetMapping(value = {"/orders/{orderId}"})
    private OrderDetailsResponse getByOrderId(@PathVariable("orderId") @NotNull String orderId) throws Exception{
        return null;
    }

    @PostMapping(value = {"/place"})
    private OrderDetailsResponse subscribeToProduct(@RequestBody OrderRequest orderRequest){
        return null;
    }

}

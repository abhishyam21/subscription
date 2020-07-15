package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.entities.OrderDetailsResponse;
import com.hackerramp.subscription.services.entities.OrderRequest;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.entities.Orders;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersRepo ordersRepo;

    @GetMapping("/showAllOrders")
    public ResponseEntity<List<Orders>> getEmployees(){
        return ResponseEntity.ok().body((List<Orders>) ordersRepo.findAll());
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

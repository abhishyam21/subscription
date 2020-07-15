package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.OrderService;
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
    private OrderService orderService;

    @GetMapping("/showAllOrders")
    public ResponseEntity<List<OrdersEntity>> getOrders(){
        List<OrdersEntity> ordersEntityList = orderService.getAllOrders();
        return ResponseEntity.ok().body(ordersEntityList);
    }

    @GetMapping(value = {"/users/{uidx}"})
    private ResponseEntity<OrderDetailsResponse> getOrdersByUidx(@PathVariable("uidx") @NotNull String uidx) {
        OrderDetailsResponse orderDetailsResponse = orderService.getOrder(uidx);
        return ResponseEntity.ok().body(orderDetailsResponse);
    }

    @GetMapping(value = {"/orders/{orderId}"})
    private ResponseEntity<OrderDetailsResponse> getByOrderId(@PathVariable("orderId") @NotNull String orderId) throws Exception{
        return null;
    }

    @PostMapping(value = {"/place"})
    private ResponseEntity<OrderDetailsResponse> subscribeToProduct(@RequestBody OrderRequest orderRequest){
        return null;
    }

}

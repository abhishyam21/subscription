package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.entities.OrderDetailsResponse;
import com.hackerramp.subscription.services.entities.OrderRequest;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping(value = {"/uidx/{uidx}"})
    private OrderDetailsResponse getOrdersByUidx(@PathVariable("uidx") @NotNull String uidx) throws Exception{

    }

    @GetMapping(value = {"/subscriptionId/{orderId}"})
    private OrderDetailsResponse getByOrderId(@PathVariable("orderId") @NotNull String orderId) throws Exception{

    }

    @PostMapping(value = {"/place"})
    private OrderDetailsResponse subscribeToProduct(@RequestBody OrderRequest orderRequest){

    }

}

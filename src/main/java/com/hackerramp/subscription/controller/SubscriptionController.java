package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.entities.SubscriptionRequest;
import com.hackerramp.subscription.services.entities.SubscriptionResponse;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.*;
import com.hackerramp.subscription.db.OrdersRepo;
import com.hackerramp.subscription.db.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @GetMapping(value = {"/uidx/{uidx}"})
    private SubscriptionResponse getSubscriptionsByUidx(@PathVariable("uidx") @NotNull String uidx) throws Exception{
        return null;
    }

    @GetMapping(value = {"/subscriptionId/{sid}"})
    private SubscriptionResponse getBySubscriptionId(@PathVariable("sid") @NotNull String sid) throws Exception{
        return null;
    }

    @PostMapping(value = {"/subscribe"})
    private SubscriptionResponse subscribeToProduct(@RequestBody SubscriptionRequest subscriptionRequest){
        return null;
    }

    @PostMapping(value = {"/editSubscription"})
    private SubscriptionResponse editProductSubscription(@RequestBody SubscriptionRequest subscriptionRequest){
        return null;
    }

    @PostMapping(value = {"/unsubscribe"})
    private SubscriptionResponse unsubscriptioe(@RequestBody SubscriptionRequest subscriptionRequest){
        return null;
    }

}

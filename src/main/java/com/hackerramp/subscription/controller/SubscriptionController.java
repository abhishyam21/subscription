package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.entities.SubscriptionRequest;
import com.hackerramp.subscription.services.entities.SubscriptionResponse;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @GetMapping(value = {"/users/{uidx}"})
    private SubscriptionResponse getSubscriptionsByUidx(@PathVariable("uidx") @NotNull String uidx) throws Exception{
        return null;
    }

    @GetMapping(value = {"/subscriptions/{sid}"})
    private SubscriptionResponse getBySubscriptionId(@PathVariable("sid") @NotNull String sid) throws Exception{
        return null;
    }

    @PostMapping(value = {"/subscribe"})
    private SubscriptionResponse subscribeToProduct(@RequestBody SubscriptionRequest subscriptionRequest){
        return null;
    }

    @PutMapping(value = {"/editSubscription"})
    private SubscriptionResponse editProductSubscription(@RequestBody SubscriptionRequest subscriptionRequest){
        return null;
    }

    @PutMapping(value = {"/unsubscribe"})
    private SubscriptionResponse unsubscribe(@RequestBody SubscriptionRequest subscriptionRequest){
        return null;
    }

}

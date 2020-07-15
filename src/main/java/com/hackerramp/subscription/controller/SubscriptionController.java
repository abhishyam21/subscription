package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.entities.SubscriptionRequest;
import com.hackerramp.subscription.services.entities.SubscriptionResponse;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @GetMapping(value = {"/uidx/{uidx}"})
    private SubscriptionResponse getSubscriptionsByUidx(@PathVariable("uidx") @NotNull String uidx) throws Exception{

    }

    @GetMapping(value = {"/subscriptionId/{sid}"})
    private SubscriptionResponse getBySubscriptionId(@PathVariable("sid") @NotNull String sid) throws Exception{

    }

    @PostMapping(value = {"/subscribe"})
    private SubscriptionResponse subscribeToProduct(@RequestBody SubscriptionRequest subscriptionRequest){

    }

    @PostMapping(value = {"/editSubscription"})
    private SubscriptionResponse editProductSubscription(@RequestBody SubscriptionRequest subscriptionRequest){

    }

    @PostMapping(value = {"/unsubscribe"})
    private SubscriptionResponse unsubscriptioe(@RequestBody SubscriptionRequest subscriptionRequest){

    }

}

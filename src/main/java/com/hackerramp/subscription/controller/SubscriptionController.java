package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.SubscriptionService;
import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import com.hackerramp.subscription.services.beans.SubscriptionResponse;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;


    @GetMapping(value = {"/users/{uidx}"})
    private ResponseEntity<SubscriptionResponse> getSubscriptionsByUidx(@PathVariable("uidx") @NotNull String uidx) throws Exception{
        SubscriptionResponse subscriptionResponse = subscriptionService.getByUserId(uidx);
        return ResponseEntity.ok().body(subscriptionResponse);
    }

    @GetMapping(value = {"/subscriptions/{sid}"})
    private ResponseEntity<SubscriptionResponse> getBySubscriptionId(@PathVariable("sid") @NotNull Integer sid) throws Exception{
        SubscriptionResponse subscriptionResponse = subscriptionService.getBySubscriptionId(sid);
        return ResponseEntity.ok().body(subscriptionResponse);
    }

    @PostMapping(value = {"/subscribe"})
    private ResponseEntity.BodyBuilder subscribeToProduct(@RequestBody SubscriptionRequest subscriptionRequest){
        subscriptionService.subscribe(subscriptionRequest);
        return ResponseEntity.ok();
    }

    @PutMapping(value = {"/editSubscription"})
    private ResponseEntity.BodyBuilder editProductSubscription(@RequestBody SubscriptionRequest subscriptionRequest){
        return ResponseEntity.ok();
    }

    @PutMapping(value = {"/unsubscribe"})
    private ResponseEntity.BodyBuilder unsubscribe(@RequestBody Integer sid){
        subscriptionService.unsubscribe(sid);
        return ResponseEntity.ok();
    }

}

package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import com.hackerramp.subscription.services.beans.SubscriptionResponse;
import com.sun.istack.internal.NotNull;
import org.springframework.web.bind.annotation.*;
import com.hackerramp.subscription.db.SubscriptionRepo;
import com.hackerramp.subscription.db.entities.SubscriptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionRepo subscriptionRepo;


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

    @GetMapping("/showAllSubscriptions")
    public ResponseEntity<List<SubscriptionEntity>> getSubscriptions(){
        return ResponseEntity.ok().body((List<SubscriptionEntity>) subscriptionRepo.findAll());
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

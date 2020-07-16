package com.hackerramp.subscription.controller;

import com.hackerramp.subscription.exception.BadInputException;
import com.hackerramp.subscription.services.SubscriptionService;
import com.hackerramp.subscription.services.beans.SubscriptionRequest;
import com.hackerramp.subscription.services.beans.SubscriptionResponse;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.hackerramp.subscription.validators.SubscriptionRequestValidator.validateRequest;

@Slf4j
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;


    @GetMapping(value = {"/users/{uidx}"})
    private ResponseEntity<SubscriptionResponse> getSubscriptionsByUidx(@PathVariable("uidx") @NotNull String uidx) {
        log.info("IN /users/{uidx} API");
        SubscriptionResponse subscriptionResponse = subscriptionService.getByUserId(uidx);
        return ResponseEntity.ok().body(subscriptionResponse);
    }

    @GetMapping(value = {"/{sid}"})
    private ResponseEntity<SubscriptionResponse> getBySubscriptionId(@PathVariable("sid") @NotNull Integer sid) {
        log.info("IN /subscriptions/{sid} API");
        SubscriptionResponse subscriptionResponse = subscriptionService.getBySubscriptionId(sid);
        return ResponseEntity.ok().body(subscriptionResponse);
    }

    @PostMapping(value = {"/subscribe"})
    private ResponseEntity<SubscriptionResponse> subscribeToProduct(@RequestBody SubscriptionRequest subscriptionRequest){
        log.info("IN /subscribe API");
        try {
            validateRequest(subscriptionRequest);
            SubscriptionResponse subscribe = subscriptionService.subscribe(subscriptionRequest);
            return ResponseEntity.created(URI.create("/subscribe")).body(subscribe);
        } catch (BadInputException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = {"/editSubscription/{sid}"})
    private ResponseEntity<SubscriptionResponse> editProductSubscription(@PathVariable("sid") @NotNull Integer sid,
                                                                             @RequestBody SubscriptionRequest subscriptionRequest){
        log.info("IN /editSubscription API");
        try {
            validateRequest(subscriptionRequest, sid);
            SubscriptionResponse subscribe = subscriptionService.editSubscription(subscriptionRequest, sid);
            return ResponseEntity.created(URI.create("/subscribe")).body(subscribe);
        } catch (BadInputException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = {"/unsubscribe/{sid}"})
    private ResponseEntity unsubscribe(@PathVariable("sid") @NotNull Integer sid){
        log.info("IN /unsubscribe API");
        try {
            String unsubscribe = subscriptionService.unsubscribe(sid);
            return ResponseEntity.ok().body(unsubscribe);
        } catch (BadInputException e) {
            return ResponseEntity.badRequest().build();
        }

    }

}

package com.api.movie.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movie.dtos.mapping.SubscriptionMapper;
import com.api.movie.dtos.request.SubscriptionRequest;
import com.api.movie.dtos.response.SubscriptionResponse;
import com.api.movie.models.Subscription;
import com.api.movie.service.SubscriptionService;
import com.api.movie.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubscriptionResponse>>> getSubscriptions() {
        try {
            List<SubscriptionResponse> subscriptions = service.getAllSubscriptions()
                    .stream()
                    .map(SubscriptionMapper::toSubscriptionResponse)
                    .toList();
            System.out.println(subscriptions.isEmpty());
            return ResponseEntity
                    .ok(new ApiResponse<>(subscriptions, "Subscriptions fetched successfully", HttpStatus.OK));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SubscriptionResponse>> createSubscription(
            @Valid @RequestBody SubscriptionRequest request) {
        try {
            SubscriptionResponse subscriptionResponse = SubscriptionMapper.toSubscriptionResponse(
                    service.createSubscription(SubscriptionMapper.toSubscription(request)));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(subscriptionResponse,
                            "Subscription created successfully", HttpStatus.CREATED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> getSubById(@PathVariable Long id) {
        try {
            Subscription subscription = service.getSubscriptionById(id);
            SubscriptionResponse subscriptionResponse = SubscriptionMapper.toSubscriptionResponse(subscription);
            return ResponseEntity.ok(new ApiResponse<>(subscriptionResponse,
                    "Subscription fetched successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteSubscription(@PathVariable Long id) {
        try {
            if (service.deleteSubscriptionById(id)) {
                return ResponseEntity.ok(new ApiResponse<>(null, "Subscription deleted successfully", HttpStatus.NO_CONTENT));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "Subscription not found", HttpStatus.NOT_FOUND));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> updateSubscription(
            @PathVariable Long id, @Valid @RequestBody SubscriptionRequest request) {
        try {
            Subscription subscription = SubscriptionMapper.toSubscription(request);
            Subscription updatedSubscription = service.updateSubscriptionById(id, subscription);
            SubscriptionResponse subscriptionResponse = SubscriptionMapper.toSubscriptionResponse(updatedSubscription);
            return ResponseEntity.ok(new ApiResponse<>(subscriptionResponse,
                    "Subscription updated successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));

        }
    }

}

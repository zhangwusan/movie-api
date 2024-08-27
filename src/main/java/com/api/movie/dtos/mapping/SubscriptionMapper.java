package com.api.movie.dtos.mapping;

import java.math.BigDecimal;
import java.util.List;

import com.api.movie.dtos.request.SubscriptionRequest;
import com.api.movie.dtos.response.SubscriptionResponse;
import com.api.movie.exception.SubscriptionNotFoundException;
import com.api.movie.models.Subscription;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SubscriptionMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Subscription toSubscription(SubscriptionRequest request) {
        if (request == null) {
            throw new SubscriptionNotFoundException("Subscription cannot be null");
        }
        
        // Convert features list to JSON
        String featuresJson;
        try {
            featuresJson = objectMapper.writeValueAsString(request.features());
        } catch (Exception e) {
            throw new SubscriptionNotFoundException("Error converting features to JSON " + e.getMessage());
        }
        
        return new Subscription(
                null,
                request.name(),
                BigDecimal.valueOf(request.price()),
                BigDecimal.valueOf(request.discount()),
                featuresJson,
                null,
                null
        );
    }

    public static SubscriptionResponse toSubscriptionResponse(Subscription subscription) {
        if (subscription == null) {
            throw new SubscriptionNotFoundException("Subscription cannot be null");
        }

        // Convert features JSON to list
        List<String> featuresList;
        try {
            featuresList = objectMapper.readValue(subscription.getFeatures(), new TypeReference<List<String>>() {});
        } catch (Exception e) {
            throw new SubscriptionNotFoundException("Error converting features from JSON" + e.getMessage());
        }
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getName(),
                subscription.getPrice(),
                featuresList,
                subscription.getDiscount(),
                subscription.getCreatedAt(),
                subscription.getUpdatedAt()
        );
    }
}

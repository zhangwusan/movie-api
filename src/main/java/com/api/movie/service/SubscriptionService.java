package com.api.movie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.movie.models.Subscription;


@Service
public interface SubscriptionService {
    List<Subscription> getAllSubscriptions();
    Subscription getSubscriptionById(Long id);
    Subscription createSubscription(Subscription subscription);
    Subscription updateSubscriptionById(Long id, Subscription subscription);
    boolean deleteSubscriptionById(Long id);
    Subscription updateDiscountById(Long id, BigDecimal discount);
    boolean isExistsSubscription(Subscription subscription);
}

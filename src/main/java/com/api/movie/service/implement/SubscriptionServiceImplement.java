package com.api.movie.service.implement;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.exception.SubscriptionAlreadyException;
import com.api.movie.exception.SubscriptionNotFoundException;
import com.api.movie.models.Subscription;
import com.api.movie.repositories.SubscriptionRepository;
import com.api.movie.service.SubscriptionService;

@Service
public class SubscriptionServiceImplement implements SubscriptionService {

    @Autowired
    private SubscriptionRepository repository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        if(isExistsSubscription(subscription)) {
            throw new SubscriptionAlreadyException("Subscription already exists");
        }
        return repository.save(subscription);
    }

    @Override
    public boolean deleteSubscriptionById(Long id) {
        boolean iSsubscription = repository.findById(id).isPresent();
        if (iSsubscription) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return repository.findAll();
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("No such subscription id " + id));
    }

    @Override
    public Subscription updateSubscriptionById(Long id, Subscription subscription) {
        return repository.findById(id).map(subscriptionDoc -> {
            subscriptionDoc.setName(subscription.getName());
            subscriptionDoc.setPrice(subscription.getPrice());
            subscriptionDoc.setFeatures(subscription.getFeatures());
            subscriptionDoc.setDiscount(subscription.getDiscount());
            return repository.save(subscriptionDoc);
        }).orElseThrow(
                () -> new SubscriptionNotFoundException("Subscription is not found with id " + subscription.getId()));
    }

    @Override
    public Subscription updateDiscountById(Long id, BigDecimal discount) {
        return repository.findById(id).map(subscriptionDoc -> {
            subscriptionDoc.setDiscount(discount);
            return repository.save(subscriptionDoc);
        }).orElseThrow(
                () -> new SubscriptionNotFoundException("Subscription is not found with id " + id));
    }

    @Override
    public boolean isExistsSubscription(Subscription subscription) {

        return repository.existsByNameAndPriceAndFeatures(
                subscription.getName(),
                subscription.getPrice(),
                subscription.getFeatures());
    }

}

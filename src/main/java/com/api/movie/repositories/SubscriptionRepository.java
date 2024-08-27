package com.api.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import com.api.movie.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsByNameAndPriceAndFeatures(String name, BigDecimal price, String features);
}

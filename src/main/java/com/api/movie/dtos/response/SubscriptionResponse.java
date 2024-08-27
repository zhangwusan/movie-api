package com.api.movie.dtos.response;

import java.time.LocalDateTime;
import java.util.List;

import java.math.BigDecimal;

public record SubscriptionResponse(
                Long id,
                String name,
                BigDecimal price,
                List<String> features,
                BigDecimal discount,
                LocalDateTime created_at,
                LocalDateTime updated_at) {
}

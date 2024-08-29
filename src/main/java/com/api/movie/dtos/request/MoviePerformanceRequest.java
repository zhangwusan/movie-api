package com.api.movie.dtos.request;

import java.math.BigDecimal;

public record MoviePerformanceRequest(
    Long movieId,
    Long views,
    Long averageWatchTime,
    Long ratingsCount,
    Double averageRating,
    BigDecimal revenueGenerated
) {
    
}

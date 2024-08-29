package com.api.movie.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MoviePerformaceResponse(
        Long id,
        Long movieId,
        Long views,
        Long averageWatchTime,
        Long ratingsCount,
        Double averageRating,
        BigDecimal revenueGenerated,
        LocalDateTime reportDate) {

}

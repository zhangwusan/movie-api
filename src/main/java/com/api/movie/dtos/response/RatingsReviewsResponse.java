package com.api.movie.dtos.response;

import java.util.UUID;
import java.time.LocalDateTime;

public record RatingsReviewsResponse(
        Long id,
        UUID userId,
        Long movieId,
        Long ratings,
        String review,
        LocalDateTime reviewDate) {

}

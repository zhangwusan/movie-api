package com.api.movie.dtos.request;

import java.util.UUID;

public record RatingsReviewsRequest(
        UUID userId,
        Long movieId,
        Long ratings,
        String review) {

}

package com.api.movie.dtos.response;

import java.time.LocalDateTime;

import java.util.UUID;

public record RecommendationResponse(
                Long id,
                UUID userId,
                Long recommendedMovieId,
                Long basedOnMovieId,
                LocalDateTime createdAt) {
}

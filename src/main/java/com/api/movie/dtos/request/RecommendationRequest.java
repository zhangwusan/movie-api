package com.api.movie.dtos.request;

import java.util.UUID;

public record RecommendationRequest(
                UUID userId,
                Long recommendedMovieId,
                Long basedOnMovieId) {

}

package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.RecommendationRequest;
import com.api.movie.dtos.response.RecommendationResponse;
import com.api.movie.models.Recommendation;
import com.api.movie.models.Movie;
import com.api.movie.models.User;

public class RecommendationMapper {

    public static Recommendation toRecommendation(RecommendationRequest request, User user, Movie recommendedMovie,
            Movie basedOnMovie) {
        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setRecommendedMovie(recommendedMovie);
        recommendation.setBasedOnMovie(basedOnMovie);
        return recommendation;
    }

    public static RecommendationResponse toRecommendationResponse(Recommendation recommendation) {
        return new RecommendationResponse(
                recommendation.getId(),
                recommendation.getUser().getId(),
                recommendation.getRecommendedMovie().getId(),
                recommendation.getBasedOnMovie().getId(),
                recommendation.getCreatedAt());
    }
}

package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.RatingsReviewsRequest;
import com.api.movie.dtos.response.RatingsReviewsResponse;
import com.api.movie.models.Movie;
import com.api.movie.models.RatingsReviews;
import com.api.movie.models.User;

public class RatingsReviewsMapper {
    public static RatingsReviews toRatingsReviews(RatingsReviewsRequest request, User user, Movie movie) {
        return new RatingsReviews(
                null,
                user,
                movie,
                request.ratings(),
                request.review(),
                null);
    }

    public static RatingsReviewsResponse toRatingsReviewsResponse(RatingsReviews ratingsReviews) {
        return new RatingsReviewsResponse(
                ratingsReviews.getId(),
                ratingsReviews.getUser().getId(),
                ratingsReviews.getMovie().getId(),
                ratingsReviews.getRating(),
                ratingsReviews.getReview(),
                ratingsReviews.getReviewDate()

        );
    }
}

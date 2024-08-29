package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.MoviePerformanceRequest;
import com.api.movie.dtos.response.MoviePerformaceResponse;
import com.api.movie.models.Movie;
import com.api.movie.models.MoviePerformance;

public class MoviePerformanceMapper {
    public static MoviePerformance toMoviePerformance(MoviePerformanceRequest request, Movie movie) {
        return new MoviePerformance(
                null,
                movie,
                request.views(),
                request.averageWatchTime(),
                request.ratingsCount(),
                request.averageRating(),
                request.revenueGenerated(),
                null);
    }

    public static MoviePerformaceResponse toMoviePerformaceResponse(MoviePerformance moviePerformance) {
        return new MoviePerformaceResponse(
                moviePerformance.getId(),
                moviePerformance.getMovie().getId(),
                moviePerformance.getViews(),
                moviePerformance.getAverageWatchTime(),
                moviePerformance.getRatingsCount(),
                moviePerformance.getAverageRating(),
                moviePerformance.getRevenueGenerated(),
                moviePerformance.getReportDate());
    }
}

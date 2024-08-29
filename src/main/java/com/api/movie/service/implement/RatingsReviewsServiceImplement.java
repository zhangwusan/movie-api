package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.movie.dtos.mapping.RatingsReviewsMapper;
import com.api.movie.dtos.request.RatingsReviewsRequest;
import com.api.movie.dtos.response.RatingsReviewsResponse;
import com.api.movie.models.Movie;
import com.api.movie.models.RatingsReviews;
import com.api.movie.models.User;
import com.api.movie.repositories.RatingsReviewsRepository;
import com.api.movie.service.MovieService;
import com.api.movie.service.RatingsReviewsService;
import com.api.movie.service.UserService;

public class RatingsReviewsServiceImplement implements RatingsReviewsService {

    @Autowired
    private RatingsReviewsRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    @Override
    public RatingsReviewsResponse createRatingsReviews(RatingsReviewsRequest request) {
        User user = userService.getUserById(request.userId());
        Movie movie = movieService.getMovieById(request.movieId());
        RatingsReviews ratingsReviews = RatingsReviewsMapper.toRatingsReviews(request, user, movie);
        RatingsReviewsResponse ratingsReviewsResponse = RatingsReviewsMapper
                .toRatingsReviewsResponse(repository.save(ratingsReviews));
        return ratingsReviewsResponse;
    }

    @Override
    public boolean deleteRatingsReviewsById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<RatingsReviewsResponse> getAllRatingsReviews() {
        return repository.findAll().stream().map(RatingsReviewsMapper::toRatingsReviewsResponse)
                .toList();
    }

    @Override
    public RatingsReviewsResponse getRatingsReviewsById(Long id) {
        RatingsReviews ratingsReviews = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("cannot find rating review by id " + id));
        return RatingsReviewsMapper.toRatingsReviewsResponse(ratingsReviews);
    }

    @Override
    public RatingsReviewsResponse updateRatingsReviewsById(Long id, RatingsReviewsRequest request) {
        User user = userService.getUserById(request.userId());
        Movie movie = movieService.getMovieById(request.movieId());
        RatingsReviews ratingsReviews = RatingsReviewsMapper.toRatingsReviews(request, user, movie);
        if (repository.existsById(id)) {
            RatingsReviews oldRatingsReviews = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("cannot find rating review by id " + id));
            oldRatingsReviews.setUser(ratingsReviews.getUser());
            oldRatingsReviews.setMovie(ratingsReviews.getMovie());
            oldRatingsReviews.setRating(ratingsReviews.getRating());
            oldRatingsReviews.setReview(ratingsReviews.getReview());
            repository.save(oldRatingsReviews);
        }

        return null;
    }

}

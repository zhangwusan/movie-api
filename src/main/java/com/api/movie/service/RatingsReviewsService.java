package com.api.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.movie.dtos.request.RatingsReviewsRequest;
import com.api.movie.dtos.response.RatingsReviewsResponse;

@Service
public interface RatingsReviewsService {
    public List<RatingsReviewsResponse> getAllRatingsReviews();

    public RatingsReviewsResponse createRatingsReviews(RatingsReviewsRequest request);

    public RatingsReviewsResponse getRatingsReviewsById(Long id);

    public RatingsReviewsResponse updateRatingsReviewsById(Long id, RatingsReviewsRequest request);

    public boolean deleteRatingsReviewsById(Long id);
}

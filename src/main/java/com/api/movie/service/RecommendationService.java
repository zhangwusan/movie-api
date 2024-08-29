package com.api.movie.service;

import org.springframework.stereotype.Service;

import com.api.movie.dtos.request.RecommendationRequest;
import com.api.movie.dtos.response.RecommendationResponse;
import java.util.List;

@Service
public interface RecommendationService {
    public RecommendationResponse createRecommendationResponse(RecommendationRequest request);

    public RecommendationResponse getRecommendationResponseById(Long id);

    public List<RecommendationResponse> getAllRecommendations();

    public RecommendationResponse updateRecommendationResponseById(Long id, RecommendationRequest request);

    public boolean deleteRecommendationResponseById(Long id);
}

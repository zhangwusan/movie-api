package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.dtos.mapping.RecommendationMapper;
import com.api.movie.dtos.request.RecommendationRequest;
import com.api.movie.dtos.response.RecommendationResponse;
import com.api.movie.models.Movie;
import com.api.movie.models.Recommendation;
import com.api.movie.models.User;
import com.api.movie.repositories.RecommendationRepository;
import com.api.movie.service.MovieService;
import com.api.movie.service.RecommendationService;
import com.api.movie.service.UserService;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImplement implements RecommendationService {

    @Autowired
    private RecommendationRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    @Override
    public RecommendationResponse createRecommendationResponse(RecommendationRequest request) {
        User user = userService.getUserById(request.userId());
        Movie recommendedMovie = movieService.getMovieById(request.recommendedMovieId());
        Movie basedOnMovie = movieService.getMovieById(request.basedOnMovieId());
        Recommendation recommendation = RecommendationMapper.toRecommendation(request, user, recommendedMovie,
                basedOnMovie);
        Recommendation savedRecommendation = repository.save(recommendation);

        return RecommendationMapper.toRecommendationResponse(savedRecommendation);
    }

    @Override
    public boolean deleteRecommendationResponseById(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<RecommendationResponse> getAllRecommendations() {
        List<Recommendation> recommendations = repository.findAll();
        return recommendations.stream()
                .map(RecommendationMapper::toRecommendationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecommendationResponse getRecommendationResponseById(Long id) {
        Recommendation recommendation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recommendation not found"));
        return RecommendationMapper.toRecommendationResponse(recommendation);
    }

    @Override
    public RecommendationResponse updateRecommendationResponseById(Long id, RecommendationRequest request) {
        Recommendation existingRecommendation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recommendation not found"));

        User user = userService.getUserById(request.userId());
        Movie recommendedMovie = movieService.getMovieById(request.recommendedMovieId());
        Movie basedOnMovie = movieService.getMovieById(request.basedOnMovieId());
        Recommendation updatedRecommendation = RecommendationMapper.toRecommendation(request, user, recommendedMovie,
                basedOnMovie);
        updatedRecommendation.setId(existingRecommendation.getId());
        Recommendation savedRecommendation = repository.save(updatedRecommendation);

        return RecommendationMapper.toRecommendationResponse(savedRecommendation);
    }

}
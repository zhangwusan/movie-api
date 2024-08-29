package com.api.movie.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movie.dtos.request.RecommendationRequest;
import com.api.movie.dtos.response.RecommendationResponse;
import com.api.movie.service.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@CacheConfig(cacheNames = "recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService service;

    @PostMapping
    public ResponseEntity<RecommendationResponse> createRecommendation(@RequestBody RecommendationRequest request) {
        RecommendationResponse response = service.createRecommendationResponse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecommendationResponse> getRecommendationById(@PathVariable Long id) {
        RecommendationResponse response = service.getRecommendationResponseById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecommendationResponse>> getAllRecommendations() {
        List<RecommendationResponse> responses = service.getAllRecommendations();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecommendationResponse> updateRecommendation(@PathVariable Long id,
            @RequestBody RecommendationRequest request) {
        RecommendationResponse response = service.updateRecommendationResponseById(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        boolean deleted = service.deleteRecommendationResponseById(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.api.movie.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movie.dtos.request.RatingsReviewsRequest;
import com.api.movie.dtos.response.RatingsReviewsResponse;
import com.api.movie.service.RatingsReviewsService;
import com.api.movie.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ratings-reviews")
public class RatingsReviewsController {
    @Autowired
    private RatingsReviewsService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RatingsReviewsResponse>>> getAllEntity() {
        return ResponseEntity.ok(new ApiResponse<>(service.getAllRatingsReviews(), "success", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RatingsReviewsResponse>> addEntity(
            @Valid @RequestBody RatingsReviewsRequest request) {
        return ResponseEntity
                .ok(new ApiResponse<>(service.createRatingsReviews(request), "success", HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingsReviewsResponse>> getEntityById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(service.getRatingsReviewsById(id), "success", HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RatingsReviewsResponse>> updateEntityById(@PathVariable Long id,
            @Valid @RequestBody RatingsReviewsRequest request) {
        return ResponseEntity
                .ok(new ApiResponse<>(service.updateRatingsReviewsById(id, request), "success", HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEntityById(@PathVariable Long id) {
        return service.deleteRatingsReviewsById(id) ? ResponseEntity.ok(new ApiResponse<>("success", HttpStatus.OK))
                : ResponseEntity.noContent().build();
    }

}

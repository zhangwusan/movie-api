package com.api.movie.controller.v1;

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

import com.api.movie.dtos.mapping.MoviePerformanceMapper;
import com.api.movie.dtos.request.MoviePerformanceRequest;
import com.api.movie.dtos.response.MoviePerformaceResponse;
import com.api.movie.models.Movie;
import com.api.movie.models.MoviePerformance;
import com.api.movie.service.MoviePerformanceService;
import com.api.movie.service.MovieService;
import com.api.movie.utils.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie-performances")
public class MoviePerformaceController {

    @Autowired
    private MoviePerformanceService service;
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MoviePerformaceResponse>>> getAllEntity() {
        List<MoviePerformaceResponse> responses = service.getAllMoviePerformances().stream()
                .map(MoviePerformanceMapper::toMoviePerformaceResponse).toList();
        return ResponseEntity.ok(new ApiResponse<>(responses, "success", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MoviePerformaceResponse>> addEntity(
            @Valid @RequestBody MoviePerformanceRequest request) {
        Movie movie = movieService.getMovieById(request.movieId());
        MoviePerformance moviePerformance = service
                .createMoviePerformance(MoviePerformanceMapper.toMoviePerformance(request, movie));
        return ResponseEntity.ok(new ApiResponse<>(MoviePerformanceMapper.toMoviePerformaceResponse(moviePerformance),
                "success", HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MoviePerformaceResponse>> getEntityById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(MoviePerformanceMapper.toMoviePerformaceResponse(service.getMoviePerformanceById(id)),
                        "success", HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MoviePerformaceResponse>> updateEntity(@PathVariable Long id,
            @RequestBody MoviePerformanceRequest request) {
        Movie movie = movieService.getMovieById(id);
        MoviePerformance updated = service.updatPerformanceById(id,
                MoviePerformanceMapper.toMoviePerformance(request, movie));
        return updated == null ? null
                : ResponseEntity.ok(new ApiResponse<>(MoviePerformanceMapper.toMoviePerformaceResponse(updated),
                        "success", HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MoviePerformaceResponse>> deleteEntity(@PathVariable Long id) {
        return service.deleteMoviePerformanceById(id) ? ResponseEntity.ok(new ApiResponse<>("success", HttpStatus.OK))
                : ResponseEntity.noContent().build();
    }
}

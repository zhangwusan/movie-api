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

import com.api.movie.dtos.mapping.WatchHistoryMapper;
import com.api.movie.dtos.request.WatchHistoryRequest;
import com.api.movie.dtos.response.WatchHistoryResponse;
import com.api.movie.models.Movie;
import com.api.movie.models.User;
import com.api.movie.models.WatchHistory;
import com.api.movie.service.MovieService;
import com.api.movie.service.UserService;
import com.api.movie.service.WatchHistoryService;
import com.api.movie.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/watch-history")
public class WatchHistoryController {

    @Autowired
    private WatchHistoryService service;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WatchHistory>>> getAllWatchHistory() {
        return ResponseEntity
                .ok(new ApiResponse<>(service.getAllWatchHistory(), "fetched successfully", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WatchHistoryResponse>> addEntity(
            @Valid @RequestBody WatchHistoryRequest request) {
        User user = userService.getUserById(request.userId());
        Movie movie = movieService.getMovieById(request.movieId());
        WatchHistory watchHistory = service.createWatchHistory(WatchHistoryMapper.toWatchHistory(request, user, movie));
        return ResponseEntity.ok(new ApiResponse<>(WatchHistoryMapper.toWatchHistoryResponse(watchHistory),
                "added successfully", HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WatchHistoryResponse>> updatEntity(@PathVariable Long id,
            @Valid @RequestBody WatchHistoryRequest request) {
        User user = userService.getUserById(request.userId());
        Movie movie = movieService.getMovieById(request.movieId());
        WatchHistory updated = service.updateWatchHistoryById(id,
                WatchHistoryMapper.toWatchHistory(request, user, movie));
        return updated != null
                ? ResponseEntity.ok(new ApiResponse<>(WatchHistoryMapper.toWatchHistoryResponse(updated),
                        "updated successfully", HttpStatus.OK))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<WatchHistoryResponse>> delete(@PathVariable Long id) {
        return service.deleteWatchHistoryById(id)
                ? ResponseEntity.ok(new ApiResponse<>("Delete successfully", HttpStatus.OK))
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WatchHistoryResponse>> getById(@PathVariable Long id) {
        return ResponseEntity
                .ok(new ApiResponse<>(WatchHistoryMapper.toWatchHistoryResponse(service.getWatchHistoryById(id)),
                        "fetched successfully", HttpStatus.OK));
    }

}

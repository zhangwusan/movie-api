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

import com.api.movie.dtos.mapping.MovieMapper;
import com.api.movie.dtos.request.MovieRequest;
import com.api.movie.dtos.response.MovieReponse;
import com.api.movie.exception.MovieAlreadyException;
import com.api.movie.models.Movie;
import com.api.movie.service.MovieService;
import com.api.movie.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MovieReponse>>> getAllMovies() {
        try {
            List<MovieReponse> response = service.getAllMovies().stream().map(MovieMapper::toMovieReponse).toList();
            return ResponseEntity.ok(new ApiResponse<>(response, "Movies fetched successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Movie>> addMovie(@Valid @RequestBody MovieRequest request) {
        try {
            Movie movie = MovieMapper.toMovie(request);
            Movie newMovie = service.createMovie(movie);
            return ResponseEntity.ok(new ApiResponse<>(newMovie, "Created successfully", HttpStatus.CREATED));
        } catch (MovieAlreadyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.CONFLICT));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovieReponse>> getById(@PathVariable Long id) {
        try {
            Movie movie = service.getMovieById(id);
            return ResponseEntity
                    .ok(new ApiResponse<>(MovieMapper.toMovieReponse(movie), "fetched successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MovieReponse>> updateMovie(@Valid @PathVariable Long id,
            @Valid @RequestBody MovieRequest request) {
        try {
            Movie movie = MovieMapper.toMovie(request);
            Movie updatedMovie = service.updateMovieById(id, movie);
            return ResponseEntity.ok(
                    new ApiResponse<>(MovieMapper.toMovieReponse(updatedMovie), "Updated successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMovie(@PathVariable Long id) {
        try {
            if (service.deleteMovieById(id)) {
                return ResponseEntity.ok(new ApiResponse<>("Movie deleted successfully", HttpStatus.OK));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("Movie not found", HttpStatus.NOT_FOUND));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

}

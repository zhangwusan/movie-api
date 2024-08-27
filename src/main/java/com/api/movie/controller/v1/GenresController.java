package com.api.movie.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.movie.dtos.mapping.GenreMapper;
import com.api.movie.dtos.request.GenreRequest;
import com.api.movie.dtos.response.GenreResponse;
import com.api.movie.models.Genres;
import com.api.movie.service.GenresService;
import com.api.movie.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenresController {

    @Autowired
    private GenresService genresService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<GenreResponse>>> getGenres() {
        try {
            List<GenreResponse> genres = genresService.getAllGenres().stream().map(GenreMapper::toGenreResponse)
                    .toList();
            return ResponseEntity.ok(new ApiResponse<>(genres, "Genres fetched successfully", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GenreResponse>> createGenre(@RequestBody GenreRequest genre) {
        try {
            Genres createdGenre = genresService.createGenres(GenreMapper.toGenre(genre));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(GenreMapper.toGenreResponse(createdGenre), "Genre created successfully",
                            HttpStatus.CREATED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GenreResponse>> getGenreById(@PathVariable Long id) {
        try {
            Genres genre = genresService.getGenresById(id);
            if (genre != null) {
                return ResponseEntity.ok(new ApiResponse<>(GenreMapper.toGenreResponse(genre),
                        "Genre fetched successfully", HttpStatus.OK));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "Genre not found", HttpStatus.NOT_FOUND));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGenreById(@PathVariable Long id) {
        try {
            boolean isDeleted = genresService.deleteGenresById(id);
            if (isDeleted) {
                return ResponseEntity.ok(new ApiResponse<>(null, "Genre deleted successfully", HttpStatus.OK));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "Genre not found", HttpStatus.NOT_FOUND));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GenreResponse>> updateGenre(@PathVariable Long id, @RequestBody GenreRequest genreRequest) {
        try {
            Genres updatedGenre = genresService.updateGenresById(id, GenreMapper.toGenre(genreRequest));
            if (updatedGenre != null) {
                return ResponseEntity.ok(new ApiResponse<>(GenreMapper.toGenreResponse(updatedGenre),
                        "Genre updated successfully", HttpStatus.OK));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "Genre not found", HttpStatus.NOT_FOUND));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}

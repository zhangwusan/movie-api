package com.api.movie.dtos.mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.movie.dtos.request.MovieRequest;
import com.api.movie.dtos.response.MovieReponse;
import com.api.movie.exception.MovieNotFoundException;
import com.api.movie.models.Genre;
import com.api.movie.models.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Movie toMovie(MovieRequest request) {
        if (request == null) {
            throw new MovieNotFoundException("Movie must not be null");
        }
        String castsJson;
        try {
            castsJson = objectMapper.writeValueAsString(request.cast());
        } catch (Exception e) {
            throw new RuntimeException("Error converting cast to JSON " + e.getMessage());
        }

        return new Movie(
                null,
                request.title(),
                request.description(),
                request.releaseDate(),
                request.duration(),
                request.director(),
                castsJson,
                request.rating(),
                request.language(),
                request.country(),
                request.posterUrl(),
                request.trailerUrl(),
                null,
                null,
                null,
                null,
                null);
    }

    public static Movie toMovie(MovieRequest request, Set<Genre> genres) {
        if (request == null) {
            throw new MovieNotFoundException("Movie must not be null");
        }
        String castsJson;
        try {
            castsJson = objectMapper.writeValueAsString(request.cast());
        } catch (Exception e) {
            throw new RuntimeException("Error converting cast to JSON " + e.getMessage());
        }

        return new Movie(
                null,
                request.title(),
                request.description(),
                request.releaseDate(),
                request.duration(),
                request.director(),
                castsJson,
                request.rating(),
                request.language(),
                request.country(),
                request.posterUrl(),
                request.trailerUrl(),
                null,
                null,
                genres,
                null,
                null);
    }

    public static MovieReponse toMovieReponse(Movie movie) {
        if (movie == null) {
            throw new MovieNotFoundException("Movie must not be null");
        }

        List<String> castsList;
        try {
            castsList = objectMapper.readValue(movie.getCast(), new TypeReference<List<String>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Error converting cast from JSON " + e.getMessage());
        }

        Set<Long> genreIds = movie.getGenres().stream()
                .map(Genre::getId)
                .collect(Collectors.toSet());
        try {
            return new MovieReponse(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getReleaseDate(),
                    movie.getDuration(),
                    movie.getDirector(),
                    castsList,
                    movie.getRating(),
                    movie.getLanguage(),
                    movie.getCountry(),
                    movie.getPosterUrl(),
                    movie.getTrailerUrl(),
                    movie.getCreatedAt(),
                    movie.getUpdatedAt(),
                    genreIds);
        } catch (Exception e) {
            throw new RuntimeException("Error converting movie to JSON " + e.getMessage());
        }
    }
}

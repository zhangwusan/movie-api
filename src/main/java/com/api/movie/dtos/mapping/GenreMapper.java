package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.GenreRequest;
import com.api.movie.dtos.response.GenreResponse;
import com.api.movie.exception.GenreNotFoundException;
import com.api.movie.models.Genre;

public class GenreMapper {
    public static Genre toGenre(GenreRequest request) {
        if (request == null) {
            throw new GenreNotFoundException("Genre cannot be null");
        }
        return new Genre(request.name());
    }
    public static GenreResponse toGenreResponse(Genre genres) {
        if (genres == null) {
            throw new GenreNotFoundException("Genre cannot be null");
        }
        return new GenreResponse(genres.getId(), genres.getName());
    }
}

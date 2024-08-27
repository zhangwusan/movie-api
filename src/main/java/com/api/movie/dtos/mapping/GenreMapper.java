package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.GenreRequest;
import com.api.movie.dtos.response.GenreResponse;
import com.api.movie.exception.GenreNotFoundException;
import com.api.movie.models.Genres;

public class GenreMapper {
    public static Genres toGenre(GenreRequest request) {
        if (request == null) {
            throw new GenreNotFoundException("Genre cannot be null");
        }
        return new Genres(request.name());
    }
    public static GenreResponse toGenreResponse(Genres genres) {
        if (genres == null) {
            throw new GenreNotFoundException("Genre cannot be null");
        }
        return new GenreResponse(genres.getId(), genres.getName());
    }
}

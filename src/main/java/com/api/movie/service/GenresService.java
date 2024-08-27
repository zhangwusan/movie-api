package com.api.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.movie.models.Genre;

@Service
public interface GenresService {
    Genre getGenresById(Long id);
    Genre getGenresByName(String name);
    Genre createGenres(Genre genres);
    Genre updateGenresById(Long id, Genre genres);
    boolean deleteGenresById(Long id);
    List<Genre> getAllGenres();
}

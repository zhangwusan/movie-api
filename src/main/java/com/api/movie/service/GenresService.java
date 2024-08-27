package com.api.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.movie.models.Genres;

@Service
public interface GenresService {
    Genres getGenresById(Long id);
    Genres getGenresByName(String name);
    Genres createGenres(Genres genres);
    Genres updateGenresById(Long id, Genres genres);
    boolean deleteGenresById(Long id);
    List<Genres> getAllGenres();
}

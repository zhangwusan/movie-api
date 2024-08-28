package com.api.movie.service;

import com.api.movie.models.Movie;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    public Movie createMovie(Movie movie);

    public Movie getMovieById(Long id);

    public Movie updateMovieById(Long id, Movie movie);

    public boolean deleteMovieById(Long id);

    public List<Movie> getAllMovies();

    public Movie getByTitle(String title);
}

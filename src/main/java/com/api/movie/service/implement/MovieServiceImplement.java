package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.exception.MovieAlreadyException;
import com.api.movie.exception.MovieNotFoundException;
import com.api.movie.models.Movie;
import com.api.movie.repositories.MovieRepository;
import com.api.movie.service.MovieService;

@Service
public class MovieServiceImplement implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Override
    public Movie createMovie(Movie movie) {
        if (repository.findByTitle(movie.getTitle()).isPresent()) {
            throw new MovieAlreadyException("Movie with the same title already exists");
        }
        return repository.save(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie is not found by id " + id));
    }

    @Override
    public Movie updateMovieById(Long id, Movie movie) {
        Movie existingMovie = repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found by id " + id));
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setCast(movie.getCast());
        existingMovie.setRating(movie.getRating());
        existingMovie.setLanguage(movie.getLanguage());
        existingMovie.setCountry(movie.getCountry());
        existingMovie.setPosterUrl(movie.getPosterUrl());
        existingMovie.setTrailerUrl(movie.getTrailerUrl());
        existingMovie.setGenres(movie.getGenres());
        return repository.save(existingMovie);
    }

    @Override
    public boolean deleteMovieById(Long id) {
        boolean movie = repository.findById(id).isPresent();
        if (movie) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    @Override
    public Movie getByTitle(String title) {
        return repository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException("No such movie title : " + title));
    }
}

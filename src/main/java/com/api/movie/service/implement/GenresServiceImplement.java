package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.exception.GenreAlreadyException;
import com.api.movie.exception.GenreNotFoundException;
import com.api.movie.models.Genre;
import com.api.movie.repositories.GenresRepository;
import com.api.movie.service.GenresService;

@Service
public class GenresServiceImplement implements GenresService {

    @Autowired
    private GenresRepository repository;

    @Override
    public Genre createGenres(Genre genres) {
        if (repository.findByName(genres.getName()).isPresent()) {
            throw new GenreAlreadyException(String.format("The genre of this %s is already exist", genres.getName()));
        }
        return repository.save(genres);
    }

    @Override
    public boolean deleteGenresById(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new GenreNotFoundException(String.format("The genre of this %s is not found", id));
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public Genre getGenresById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(String.format("The genre id : %s isn't found.", id)));
    }

    @Override
    public Genre getGenresByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new GenreNotFoundException(String.format("The genre name : %s isn't found.", name)));
    }

    @Override
    public Genre updateGenresById(Long id, Genre genres) {
        Genre genreDoc = this.getGenresById(id);
        genreDoc.setName(genres.getName());
        return repository.save(genreDoc);
    }

    @Override
    public List<Genre> getAllGenres() {
        return repository.findAll();
    }
}

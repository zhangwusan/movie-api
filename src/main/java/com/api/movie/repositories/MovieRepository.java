package com.api.movie.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.movie.models.Movie;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}

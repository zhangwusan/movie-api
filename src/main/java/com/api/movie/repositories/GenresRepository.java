package com.api.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.movie.models.Genre;

import java.util.Optional;


@Repository
public interface GenresRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}

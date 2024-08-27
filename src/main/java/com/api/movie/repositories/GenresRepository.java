package com.api.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.movie.models.Genres;

import java.util.Optional;


@Repository
public interface GenresRepository extends JpaRepository<Genres, Long> {
    Optional<Genres> findByName(String name);
}

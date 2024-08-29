package com.api.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.movie.models.MoviePerformance;

@Repository
public interface MoviePerformanceRepository extends JpaRepository<MoviePerformance, Long> {

}

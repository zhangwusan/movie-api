package com.api.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.movie.models.MoviePerformance;

@Service
public interface MoviePerformanceService {
    public List<MoviePerformance> getAllMoviePerformances();

    public MoviePerformance createMoviePerformance(MoviePerformance moviePerformance);

    public MoviePerformance getMoviePerformanceById(Long id);

    public MoviePerformance updatPerformanceById(Long id, MoviePerformance moviePerformance);

    public boolean deleteMoviePerformanceById(Long id);

}

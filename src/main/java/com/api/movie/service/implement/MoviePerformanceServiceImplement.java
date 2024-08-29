package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.models.MoviePerformance;
import com.api.movie.repositories.MoviePerformanceRepository;
import com.api.movie.service.MoviePerformanceService;

@Service
public class MoviePerformanceServiceImplement implements MoviePerformanceService {

    @Autowired
    private MoviePerformanceRepository repository;

    @Override
    public MoviePerformance createMoviePerformance(MoviePerformance moviePerformance) {
        return repository.save(moviePerformance);
    }

    @Override
    public boolean deleteMoviePerformanceById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<MoviePerformance> getAllMoviePerformances() {
        return repository.findAll();
    }

    @Override
    public MoviePerformance getMoviePerformanceById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found movie performance by id " + id));
    }

    @Override
    public MoviePerformance updatPerformanceById(Long id, MoviePerformance moviePerformance) {
        if (repository.existsById(id)) {
            MoviePerformance oldMoviePerformance = this.getMoviePerformanceById(id);
            oldMoviePerformance.setMovie(moviePerformance.getMovie());
            oldMoviePerformance.setAverageWatchTime(moviePerformance.getAverageWatchTime());
            oldMoviePerformance.setAverageRating(moviePerformance.getAverageRating());
            oldMoviePerformance.setRatingsCount(moviePerformance.getRatingsCount());
            oldMoviePerformance.setReportDate(moviePerformance.getReportDate());
            return repository.save(oldMoviePerformance);
        }
        return null;
    }

}

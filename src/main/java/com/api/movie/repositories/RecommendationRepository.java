package com.api.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.movie.models.Recommendation;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}

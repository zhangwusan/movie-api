package com.api.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.movie.models.RatingsReviews;

public interface RatingsReviewsRepository extends JpaRepository<RatingsReviews, Long> {

}

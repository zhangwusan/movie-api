package com.api.movie.dtos.request;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

public record MovieRequest(
                @NotBlank(message = "Title is mandatory") @Size(max = 255, message = "Title must be less than 255 characters and unique") String title,
                String description,
                @PastOrPresent(message = "Release date cannot be in the future") LocalDate releaseDate,
                @Positive(message = "Duration must be a positive number") Long duration,
                @Size(max = 255, message = "Director name must be less than 255 characters") String director,
                String[] cast,
                @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0") @DecimalMax(value = "10.0", inclusive = true, message = "Rating must be at most 10.0") Float rating,
                @Size(max = 50, message = "Language must be less than 50 characters") String language,
                @Size(max = 50, message = "Country must be less than 50 characters") String country,
                @URL(message = "Poster URL must be a valid URL") @NotBlank(message = "poster is requried") String posterUrl,
                @URL(message = "Trailer URL must be a valid URL") String trailerUrl,
                Set<Long> genreIds) {
}

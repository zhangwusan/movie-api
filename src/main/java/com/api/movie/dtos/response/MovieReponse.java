package com.api.movie.dtos.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MovieReponse(
                Long id,
                String title,
                String description,
                LocalDate releaseDate,
                Long duration,
                String director,
                List<String> casts,
                Float rating,
                String language,
                String country,
                String posterUrl,
                String trailerUrl,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}

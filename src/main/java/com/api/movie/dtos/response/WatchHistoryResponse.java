package com.api.movie.dtos.response;

import java.sql.Timestamp;
import java.util.UUID;

public record WatchHistoryResponse(
        Long id,
        UUID userId,
        Long movieId,
        Timestamp watchedAt,
        Timestamp finishedAt,
        Long durationWatched) {
}
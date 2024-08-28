package com.api.movie.dtos.request;

import java.sql.Timestamp;
import java.util.UUID;

public record WatchHistoryRequest(
        UUID userId,
        Long movieId,
        Timestamp watchedAt,
        Timestamp finishedAt,
        Long durationWatched) {
}
package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.WatchHistoryRequest;
import com.api.movie.dtos.response.WatchHistoryResponse;
import com.api.movie.models.Movie;
import com.api.movie.models.User;
import com.api.movie.models.WatchHistory;

public class WatchHistoryMapper {
    public static WatchHistory toWatchHistory(WatchHistoryRequest request, User user, Movie movie) {
        return new WatchHistory(
                null,
                user,
                movie,
                request.watchedAt(),
                request.finishedAt(),
                null);
    }

    public static WatchHistoryResponse toWatchHistoryResponse(WatchHistory watchHistory) {
        return new WatchHistoryResponse(
                watchHistory.getId(),
                watchHistory.getUser().getId(),
                watchHistory.getMovie().getId(),
                watchHistory.getWatchedAt(),
                watchHistory.getFinishedAt(),
                watchHistory.getDurationWatched());
    }
}

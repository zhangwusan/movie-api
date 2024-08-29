package com.api.movie.dtos.request;

import java.util.UUID;

public record UserActivityLogsResquest(
        UUID userId,
        String activityType,
        String[] details) {

}

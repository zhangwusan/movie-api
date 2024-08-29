package com.api.movie.dtos.response;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

public record UserActivityLogsResponse(
        Long id,
        UUID userId,
        String activityType,
        LocalDateTime activityTimeStamp,
        List<String> details) {

}

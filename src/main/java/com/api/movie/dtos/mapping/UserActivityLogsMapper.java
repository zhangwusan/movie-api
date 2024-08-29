package com.api.movie.dtos.mapping;

import com.api.movie.dtos.request.UserActivityLogsResquest;
import com.api.movie.dtos.response.UserActivityLogsResponse;
import com.api.movie.models.User;
import com.api.movie.models.UserActivityLogs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class UserActivityLogsMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static UserActivityLogs toUserActivityLogs(UserActivityLogsResquest resquest, User user) {
        String detailsString;
        try {
            detailsString = mapper.writeValueAsString(resquest.details());
        } catch (Exception e) {
            throw new RuntimeException("Error writing details array to string");
        }

        return new UserActivityLogs(
                null,
                user,
                resquest.activityType(),
                null,
                detailsString);
    }

    public static UserActivityLogsResponse toUserActivityLogsResponse(UserActivityLogs userActivityLogs) {
        List<String> detailsList;
        try {
            detailsList = mapper.readValue(userActivityLogs.getDetails(), new TypeReference<List<String>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("error converting string to list");
        }
        return new UserActivityLogsResponse(
                userActivityLogs.getId(),
                userActivityLogs.getUser().getId(),
                userActivityLogs.getActivityType(),
                userActivityLogs.getActivityTimeStamp(),
                detailsList);
    }

}

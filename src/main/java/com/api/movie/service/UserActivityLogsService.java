package com.api.movie.service;

import org.springframework.stereotype.Service;

import com.api.movie.models.UserActivityLogs;

import java.util.List;

@Service
public interface UserActivityLogsService {
    public List<UserActivityLogs> getAllUserActivityLogs();

    public UserActivityLogs getUserActivityLogsById(Long id);

    public UserActivityLogs createUserActivityLogs(UserActivityLogs userActivityLogs);

    public UserActivityLogs updateActivityLogsById(Long id, UserActivityLogs userActivityLogs);

    public boolean deleteUserActivityLogs(Long id);
}

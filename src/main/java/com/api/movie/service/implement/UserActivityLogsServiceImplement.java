package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.models.UserActivityLogs;
import com.api.movie.repositories.UserActivityLogsRepository;
import com.api.movie.service.UserActivityLogsService;

@Service
public class UserActivityLogsServiceImplement implements UserActivityLogsService {

    @Autowired
    private UserActivityLogsRepository repository;

    @Override
    public UserActivityLogs createUserActivityLogs(UserActivityLogs userActivityLogs) {
        return repository.save(userActivityLogs);
    }

    @Override
    public boolean deleteUserActivityLogs(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<UserActivityLogs> getAllUserActivityLogs() {
        return repository.findAll();
    }

    @Override
    public UserActivityLogs getUserActivityLogsById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User activity logs not found"));
    }

    @Override
    public UserActivityLogs updateActivityLogsById(Long id, UserActivityLogs userActivityLogs) {
        if (repository.existsById(id)) {
            UserActivityLogs oldActivityLogs = this.getUserActivityLogsById(id);
            oldActivityLogs.setActivityType(userActivityLogs.getActivityType());
            oldActivityLogs.setDetails(userActivityLogs.getDetails());
            repository.save(oldActivityLogs);
        }
        return null;
    }

}

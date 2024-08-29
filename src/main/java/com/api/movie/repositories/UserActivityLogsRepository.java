package com.api.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.movie.models.UserActivityLogs;

@Repository
public interface UserActivityLogsRepository extends JpaRepository<UserActivityLogs, Long> {
    
}

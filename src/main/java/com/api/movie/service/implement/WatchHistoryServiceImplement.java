package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.movie.models.WatchHistory;
import com.api.movie.repositories.WatchHistoryRepository;
import com.api.movie.service.WatchHistoryService;

public class WatchHistoryServiceImplement implements WatchHistoryService {

    @Autowired
    private WatchHistoryRepository repository;

    @Override
    public boolean clear() {
        repository.deleteAll();
        return true;
    }

    @Override
    public WatchHistory createWatchHistory(WatchHistory watchHistory) {
        watchHistory.calculateDurationWatched();
        return repository.save(watchHistory);
    }

    @Override
    public boolean deleteWatchHistoryById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<WatchHistory> getAllWatchHistory() {
        return repository.findAll();
    }

    @Override
    public WatchHistory getWatchHistoryById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Watch history not found for id " + id));
    }

    @Override
    public WatchHistory updateWatchHistoryById(Long id, WatchHistory watchHistory) {
        if (repository.existsById(id)) {
            watchHistory.setId(id);
            watchHistory.calculateDurationWatched();
            return repository.save(watchHistory);
        }
        return null;
    }

}

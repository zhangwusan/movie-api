package com.api.movie.service;

import org.springframework.stereotype.Service;

import com.api.movie.models.WatchHistory;

import java.util.List;

@Service
public interface WatchHistoryService {
    public List<WatchHistory> getAllWatchHistory();
    public WatchHistory getWatchHistoryById(Long id);
    public WatchHistory updateWatchHistoryById(Long id, WatchHistory watchHistory);
    public boolean deleteWatchHistoryById(Long id);
    public boolean clear();
    public WatchHistory createWatchHistory(WatchHistory watchHistory);
}

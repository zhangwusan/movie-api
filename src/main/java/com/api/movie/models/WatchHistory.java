package com.api.movie.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "watch_history")
public class WatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "watched_at", nullable = false)
    private Timestamp watchedAt;

    @Column(name = "finished_at", nullable = false)
    private Timestamp finishedAt;

    @Column(name = "duration_watched", nullable = false)
    private Long durationWatched;

    public void calculateDurationWatched() {
        if (watchedAt != null && finishedAt != null) {
            Long milliseconds = finishedAt.getTime() - watchedAt.getTime();
            this.durationWatched = milliseconds / 1000 / 60;
        }
    }
}

package com.api.movie.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "movie_performances")
public class MoviePerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    private Long views;
    @Column(name = "average_watch_time", nullable = false)
    private Long averageWatchTime;
    @Column(name = "rating_count")
    private Long ratingsCount;
    @Column(name = "average_rating")
    private Double averageRating;
    @Column(name = "revenue_generated", precision = 10, scale = 2)
    private BigDecimal revenueGenerated;
    @Column(name = "report_date")
    private LocalDateTime reportDate;
}

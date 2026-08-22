package com.tracking.backend.entity;

import com.tracking.backend.entity.enums.SleepQuality;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "sleep_logs")
@Getter
@Setter
public class SleepLog extends BaseEntity {

    @Column(name = "bed_time", nullable = false)
    private Instant bedTime;

    @Column(name = "wake_time", nullable = false)
    private Instant wakeTime;

    @Column(name = "total_time", nullable = false, length = 5)
    private String totalTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SleepQuality quality;
}

package com.tracking.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exercise_logs")
@Getter
@Setter
public class ExerciseLog extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String activity;

    @Column(name = "total_time", nullable = false, length = 5)
    private String totalTime;
}

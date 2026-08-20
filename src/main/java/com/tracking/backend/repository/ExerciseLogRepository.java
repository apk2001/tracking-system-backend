package com.tracking.backend.repository;

import com.tracking.backend.entity.ExerciseLog;

import java.util.List;

public interface ExerciseLogRepository extends TrackingRepository<ExerciseLog> {

    List<ExerciseLog> findTop10ByOrderByCreatedAtDesc();
}

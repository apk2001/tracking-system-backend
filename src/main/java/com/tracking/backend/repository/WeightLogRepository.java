package com.tracking.backend.repository;

import com.tracking.backend.entity.WeightLog;

import java.util.List;

public interface WeightLogRepository extends TrackingRepository<WeightLog> {

    List<WeightLog> findTop10ByOrderByCreatedAtDesc();
}

package com.tracking.backend.repository;

import com.tracking.backend.entity.FoodLog;

import java.util.List;

public interface FoodLogRepository extends TrackingRepository<FoodLog> {

    List<FoodLog> findTop10ByOrderByEatenAtDesc();
}

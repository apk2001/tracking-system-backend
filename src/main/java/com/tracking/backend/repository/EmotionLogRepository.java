package com.tracking.backend.repository;

import com.tracking.backend.entity.EmotionLog;

import java.util.List;

public interface EmotionLogRepository extends TrackingRepository<EmotionLog> {

    List<EmotionLog> findTop10ByOrderByCreatedAtDesc();
}

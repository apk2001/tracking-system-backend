package com.tracking.backend.repository;

import com.tracking.backend.entity.ThoughtLog;

import java.util.List;

public interface ThoughtLogRepository extends TrackingRepository<ThoughtLog> {

    List<ThoughtLog> findTop10ByOrderByCreatedAtDesc();
}

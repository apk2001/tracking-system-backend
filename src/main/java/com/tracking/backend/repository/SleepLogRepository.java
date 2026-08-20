package com.tracking.backend.repository;

import com.tracking.backend.entity.SleepLog;

import java.util.List;

public interface SleepLogRepository extends TrackingRepository<SleepLog> {

    List<SleepLog> findTop10ByOrderByBedTimeDesc();
}

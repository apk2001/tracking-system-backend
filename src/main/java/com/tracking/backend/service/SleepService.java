package com.tracking.backend.service;

import com.tracking.backend.dto.sleep.SleepRequestDTO;
import com.tracking.backend.dto.sleep.SleepResponseDTO;
import com.tracking.backend.entity.SleepLog;
import com.tracking.backend.mapper.SleepMapper;
import com.tracking.backend.repository.SleepLogRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SleepService extends AbstractTrackingService<SleepLog, SleepRequestDTO, SleepResponseDTO> {

    private final SleepLogRepository sleepLogRepository;

    public SleepService(SleepLogRepository sleepLogRepository, SleepMapper sleepMapper) {
        super(sleepLogRepository, sleepMapper);
        this.sleepLogRepository = sleepLogRepository;
    }

    @Override
    protected void prepareEntity(SleepLog entity, SleepRequestDTO request) {
        entity.setTotalTime(calculateTotalTime(request.bedTime(), request.wakeTime()));
    }

    private String calculateTotalTime(LocalDateTime bedTime, LocalDateTime wakeTime) {
        LocalDateTime effectiveWakeTime = wakeTime.isBefore(bedTime) ? wakeTime.plusDays(1) : wakeTime;
        Duration duration = Duration.between(bedTime, effectiveWakeTime);
        return String.format("%02d:%02d", duration.toHours(), duration.toMinutesPart());
    }

    @Override
    protected List<SleepLog> fetchRecent() {
        return sleepLogRepository.findTop10ByOrderByBedTimeDesc();
    }
}

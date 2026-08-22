package com.tracking.backend.mapper;

import com.tracking.backend.config.RequestTimeZoneHolder;
import com.tracking.backend.dto.sleep.SleepRequestDTO;
import com.tracking.backend.dto.sleep.SleepResponseDTO;
import com.tracking.backend.entity.SleepLog;
import org.springframework.stereotype.Component;

@Component
public class SleepMapper implements EntityMapper<SleepLog, SleepRequestDTO, SleepResponseDTO> {

    private final RequestTimeZoneHolder requestTimeZoneHolder;

    public SleepMapper(RequestTimeZoneHolder requestTimeZoneHolder) {
        this.requestTimeZoneHolder = requestTimeZoneHolder;
    }

    @Override
    public SleepLog toEntity(SleepRequestDTO request) {
        SleepLog entity = new SleepLog();
        entity.setBedTime(request.bedTime().atZone(requestTimeZoneHolder.getZoneId()).toInstant());
        entity.setWakeTime(request.wakeTime().atZone(requestTimeZoneHolder.getZoneId()).toInstant());
        entity.setQuality(request.quality());
        return entity;
    }

    @Override
    public SleepResponseDTO toResponse(SleepLog entity) {
        return new SleepResponseDTO(
                entity.getId(),
                entity.getBedTime().atZone(requestTimeZoneHolder.getZoneId()).toLocalDateTime(),
                entity.getWakeTime().atZone(requestTimeZoneHolder.getZoneId()).toLocalDateTime(),
                entity.getTotalTime(),
                entity.getQuality());
    }
}

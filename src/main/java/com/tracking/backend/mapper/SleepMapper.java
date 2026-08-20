package com.tracking.backend.mapper;

import com.tracking.backend.dto.sleep.SleepRequestDTO;
import com.tracking.backend.dto.sleep.SleepResponseDTO;
import com.tracking.backend.entity.SleepLog;
import org.springframework.stereotype.Component;

@Component
public class SleepMapper implements EntityMapper<SleepLog, SleepRequestDTO, SleepResponseDTO> {

    @Override
    public SleepLog toEntity(SleepRequestDTO request) {
        SleepLog entity = new SleepLog();
        entity.setBedTime(request.bedTime());
        entity.setWakeTime(request.wakeTime());
        entity.setQuality(request.quality());
        return entity;
    }

    @Override
    public SleepResponseDTO toResponse(SleepLog entity) {
        return new SleepResponseDTO(
                entity.getId(),
                entity.getBedTime(),
                entity.getWakeTime(),
                entity.getTotalTime(),
                entity.getQuality());
    }
}

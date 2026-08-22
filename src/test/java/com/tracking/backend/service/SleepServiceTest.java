package com.tracking.backend.service;

import com.tracking.backend.dto.sleep.SleepRequestDTO;
import com.tracking.backend.dto.sleep.SleepResponseDTO;
import com.tracking.backend.entity.SleepLog;
import com.tracking.backend.entity.enums.SleepQuality;
import com.tracking.backend.mapper.SleepMapper;
import com.tracking.backend.repository.SleepLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SleepServiceTest {

    @Mock
    private SleepLogRepository sleepLogRepository;

    @Mock
    private SleepMapper sleepMapper;

    @InjectMocks
    private SleepService sleepService;

    @Test
    void calculatesTotalTimeWithinSameDay() {
        SleepRequestDTO request = new SleepRequestDTO(
                LocalDateTime.of(2026, 8, 20, 13, 0),
                LocalDateTime.of(2026, 8, 20, 14, 30),
                SleepQuality.GOOD);
        SleepLog entity = new SleepLog();

        when(sleepMapper.toEntity(request)).thenReturn(entity);
        when(sleepLogRepository.save(entity)).thenReturn(entity);
        when(sleepMapper.toResponse(entity)).thenAnswer(invocation -> new SleepResponseDTO(
                1L, null, null, entity.getTotalTime(), entity.getQuality()));

        SleepResponseDTO response = sleepService.create(request);

        assertThat(response.totalTime()).isEqualTo("01:30");
    }

    @Test
    void calculatesTotalTimeAcrossMidnight() {
        SleepRequestDTO request = new SleepRequestDTO(
                LocalDateTime.of(2026, 8, 20, 23, 30),
                LocalDateTime.of(2026, 8, 21, 6, 15),
                SleepQuality.OK);
        SleepLog entity = new SleepLog();

        when(sleepMapper.toEntity(request)).thenReturn(entity);
        when(sleepLogRepository.save(entity)).thenReturn(entity);
        when(sleepMapper.toResponse(entity)).thenAnswer(invocation -> new SleepResponseDTO(
                1L, null, null, entity.getTotalTime(), entity.getQuality()));

        SleepResponseDTO response = sleepService.create(request);

        assertThat(response.totalTime()).isEqualTo("06:45");
    }
}

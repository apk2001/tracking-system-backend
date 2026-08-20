package com.tracking.backend.service;

import com.tracking.backend.dto.weight.WeightRequestDTO;
import com.tracking.backend.dto.weight.WeightResponseDTO;
import com.tracking.backend.entity.WeightLog;
import com.tracking.backend.mapper.WeightMapper;
import com.tracking.backend.repository.WeightLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeightServiceTest {

    @Mock
    private WeightLogRepository weightLogRepository;

    @Mock
    private WeightMapper weightMapper;

    @InjectMocks
    private WeightService weightService;

    @Test
    void createsAndReturnsWeightEntry() {
        WeightRequestDTO request = new WeightRequestDTO(new BigDecimal("72.50"));
        WeightLog entity = new WeightLog();
        WeightResponseDTO responseDto = new WeightResponseDTO(1L, "20/08/2026", new BigDecimal("72.50"));

        when(weightMapper.toEntity(request)).thenReturn(entity);
        when(weightLogRepository.save(entity)).thenReturn(entity);
        when(weightMapper.toResponse(entity)).thenReturn(responseDto);

        WeightResponseDTO result = weightService.create(request);

        assertThat(result.weightKg()).isEqualByComparingTo("72.50");
    }

    @Test
    void findRecentMapsAllEntities() {
        WeightLog entity = new WeightLog();
        WeightResponseDTO responseDto = new WeightResponseDTO(1L, "20/08/2026", new BigDecimal("72.50"));

        when(weightLogRepository.findTop10ByOrderByCreatedAtDesc()).thenReturn(List.of(entity));
        when(weightMapper.toResponse(entity)).thenReturn(responseDto);

        List<WeightResponseDTO> results = weightService.findRecent();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).date()).isEqualTo("20/08/2026");
    }
}

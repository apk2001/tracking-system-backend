package com.tracking.backend.service;

import com.tracking.backend.mapper.EntityMapper;
import com.tracking.backend.repository.TrackingRepository;

import java.util.List;

public abstract class AbstractTrackingService<E, ReqDTO, ResDTO> implements TrackingService<ReqDTO, ResDTO> {

    protected final TrackingRepository<E> repository;
    protected final EntityMapper<E, ReqDTO, ResDTO> mapper;

    protected AbstractTrackingService(TrackingRepository<E> repository, EntityMapper<E, ReqDTO, ResDTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResDTO create(ReqDTO request) {
        E entity = mapper.toEntity(request);
        prepareEntity(entity, request);
        E saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public List<ResDTO> findRecent() {
        return fetchRecent().stream().map(mapper::toResponse).toList();
    }

    /**
     * Hook for subclasses that need to derive fields before persisting (e.g. sleep duration).
     * No-op by default.
     */
    protected void prepareEntity(E entity, ReqDTO request) {
    }

    protected abstract List<E> fetchRecent();
}

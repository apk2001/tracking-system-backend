package com.tracking.backend.mapper;

public interface EntityMapper<E, ReqDTO, ResDTO> {

    E toEntity(ReqDTO request);

    ResDTO toResponse(E entity);
}

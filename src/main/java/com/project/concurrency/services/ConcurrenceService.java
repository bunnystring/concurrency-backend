package com.project.concurrency.services;

import com.project.concurrency.model.dto.ConcurrenceDto;
import com.project.concurrency.model.dto.ConcurrenceListDto;


import java.util.UUID;

public interface ConcurrenceService {
    ConcurrenceListDto findAll();

    ConcurrenceDto findById(UUID id);

    ConcurrenceDto save(ConcurrenceDto concurrence);

    ConcurrenceDto update(UUID id, ConcurrenceDto dto);

    void deleteById(UUID id);
}

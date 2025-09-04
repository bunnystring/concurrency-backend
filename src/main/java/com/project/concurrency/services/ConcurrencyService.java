package com.project.concurrency.services;

import com.project.concurrency.model.dto.ConcurrencyDto;
import com.project.concurrency.model.dto.ConcurrencyListDto;


import java.util.UUID;

public interface ConcurrencyService {

    ConcurrencyListDto findAll();

    ConcurrencyDto findById(UUID id);

    ConcurrencyDto save(ConcurrencyDto concurrence);

    ConcurrencyDto update(UUID id, ConcurrencyDto dto);

    void deleteById(UUID id);
}

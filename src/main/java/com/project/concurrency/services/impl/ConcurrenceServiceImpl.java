package com.project.concurrency.services.impl;

import com.project.concurrency.entity.Concurrence;
import com.project.concurrency.model.dto.ConcurrenceDto;
import com.project.concurrency.model.dto.ConcurrenceListDto;
import com.project.concurrency.repository.ConcurrenceRepository;
import com.project.concurrency.services.ConcurrenceService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ConcurrenceServiceImpl implements ConcurrenceService {

    private final ConcurrenceRepository concurrenceRepository;

    public ConcurrenceServiceImpl(ConcurrenceRepository concurrenceRepository) {
        this.concurrenceRepository = concurrenceRepository;
    }

    @Override
    public ConcurrenceListDto findAll() {
        List<ConcurrenceDto> dtos = concurrenceRepository.findAll()
                .stream()
                .map(concurrence -> {
                    ConcurrenceDto dto = new ConcurrenceDto();
                    dto.setId(concurrence.getId());
                    dto.setName(concurrence.getName());
                    dto.setStatus(concurrence.getStatus());
                    return dto;
                })
                .toList();

        ConcurrenceListDto listDto = new ConcurrenceListDto();
        listDto.setItems(dtos);

        return listDto;
    }

    @Override
    public ConcurrenceDto findById(UUID id) {
        Concurrence concurrence = concurrenceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no se encontró ninguna " +
                        "concurrence con el id proporcionado"));

        ConcurrenceDto dto = new ConcurrenceDto();
        dto.setName(concurrence.getName());
        dto.setStatus(concurrence.getStatus());

         return dto;
    }

    @Override
    public ConcurrenceDto save(ConcurrenceDto dto) {
        Concurrence concurrence = new Concurrence();

        // Construir objeto
        concurrence.setName(dto.getName());
        concurrence.setStatus(dto.getStatus());

        // Guardar entidad
        Concurrence result = concurrenceRepository.save(concurrence);

        // Mapeo dto respuesta
        ConcurrenceDto responseDto = new ConcurrenceDto();
        responseDto.setName(result.getName());
        responseDto.setStatus(result.getStatus());

        return responseDto;
    }

    @Override
    public ConcurrenceDto update(UUID id, ConcurrenceDto dto) {

        Concurrence existing = concurrenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró la concurrence con el id proporcionado"));

        existing.setName(dto.getName());
        existing.setStatus(dto.getStatus());

        Concurrence updated = concurrenceRepository.save(existing);

        // Mapea a DTO de respuesta
        ConcurrenceDto responseDto = new ConcurrenceDto();
        responseDto.setName(updated.getName());
        responseDto.setStatus(updated.getStatus());

        return responseDto;
    }

    @Override
    public void deleteById(UUID id) {
        concurrenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró la concurrence con el id proporcionado"));

        concurrenceRepository.deleteById(id);
    }
}

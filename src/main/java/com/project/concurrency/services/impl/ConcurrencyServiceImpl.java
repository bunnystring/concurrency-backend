package com.project.concurrency.services.impl;

import com.project.concurrency.entity.Concurrency;
import com.project.concurrency.exception.ConcurrencyException;
import com.project.concurrency.model.dto.ConcurrencyDto;
import com.project.concurrency.model.dto.ConcurrencyListDto;
import com.project.concurrency.repository.ConcurrencyRepository;
import com.project.concurrency.services.ConcurrencyService;
import com.project.concurrency.util.MessageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Implementación del servicio de concurrencia.
 *
 * Proporciona operaciones CRUD para la entidad {@link Concurrency},
 * gestionando la lógica de negocio y el manejo de excepciones personalizadas.
 */
 @Service
public class ConcurrencyServiceImpl implements ConcurrencyService {

    /**
     * ConcurrencyRepository: respositorio de concurrencia.
     */
    private final ConcurrencyRepository concurrencyRepository;

    /**
     * Constructor de la implementación del servicio de concurrencia.
     *
     * @param concurrencyRepository Repositorio para operaciones con la entidad Concurrency.
     */
    public ConcurrencyServiceImpl(ConcurrencyRepository concurrencyRepository) {
        this.concurrencyRepository = concurrencyRepository;
    }

    /**
     * Obtiene todas las entidades de concurrencia y las transforma en un objeto de lista de DTO.
     *
     * @return {@link ConcurrencyListDto} con la colección de concurrencias.
     */
    @Override
    public ConcurrencyListDto findAll() {
        List<ConcurrencyDto> dtos = concurrencyRepository.findAll()
                .stream()
                .map(concurrency -> {
                    ConcurrencyDto dto = new ConcurrencyDto();
                    dto.setId(concurrency.getId());
                    dto.setName(concurrency.getName());
                    dto.setStatus(concurrency.getStatus());
                    return dto;
                })
                .toList();

        ConcurrencyListDto listDto = new ConcurrencyListDto();
        listDto.setItems(dtos);

        return listDto;
    }

    /**
     * Busca una concurrencia por su identificador único.
     *
     * @param id Identificador único de la concurrencia.
     * @return {@link ConcurrencyDto} con los datos de la concurrencia encontrada.
     * @throws ConcurrencyException Si la concurrencia no existe.
     */
    @Override
    public ConcurrencyDto findById(UUID id) {
        Concurrency concurrency = concurrencyRepository.findById(id).orElseThrow(
                () -> new ConcurrencyException(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.toString(),
                        MessageException.CONCURRENCY_NOT_FOUND));

        ConcurrencyDto dto = new ConcurrencyDto();
        dto.setName(concurrency.getName());
        dto.setStatus(concurrency.getStatus());

         return dto;
    }

    /**
     * Registra una nueva concurrencia en el sistema.
     *
     * @param dto DTO con los datos de la concurrencia a guardar.
     * @return {@link ConcurrencyDto} con los datos de la concurrencia registrada.
     */
    @Override
    public ConcurrencyDto save(ConcurrencyDto dto) {
        Concurrency concurrency = new Concurrency();

        // Construir objeto
        concurrency.setName(dto.getName());
        concurrency.setStatus(dto.getStatus());

        // Guardar entidad
        Concurrency result = concurrencyRepository.save(concurrency);

        // Mapeo dto respuesta
        ConcurrencyDto responseDto = new ConcurrencyDto();
        responseDto.setName(result.getName());
        responseDto.setStatus(result.getStatus());

        return responseDto;
    }

    /**
     * Actualiza los datos de una concurrencia existente.
     *
     * @param id Identificador único de la concurrencia a actualizar.
     * @param dto DTO con los nuevos datos.
     * @return {@link ConcurrencyDto} con los datos actualizados.
     * @throws ConcurrencyException Si la concurrencia no existe.
     */
    @Override
    public ConcurrencyDto update(UUID id, ConcurrencyDto dto) {

        Concurrency existing = concurrencyRepository.findById(id)
                .orElseThrow(() -> new ConcurrencyException(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.toString(),
                        MessageException.CONCURRENCY_NOT_FOUND));

        existing.setName(dto.getName());
        existing.setStatus(dto.getStatus());

        Concurrency updated = concurrencyRepository.save(existing);

        // Mapea a DTO de respuesta
        ConcurrencyDto responseDto = new ConcurrencyDto();
        responseDto.setName(updated.getName());
        responseDto.setStatus(updated.getStatus());

        return responseDto;
    }

    /**
     * Elimina una concurrencia por su identificador único.
     *
     * @param id Identificador único de la concurrencia a eliminar.
     * @throws ConcurrencyException Si la concurrencia no existe.
     */
    @Override
    public void deleteById(UUID id) {

        concurrencyRepository.findById(id)
                .orElseThrow(() -> new ConcurrencyException(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.toString(),
                        MessageException.CONCURRENCY_NOT_FOUND));

        concurrencyRepository.deleteById(id);
    }
}

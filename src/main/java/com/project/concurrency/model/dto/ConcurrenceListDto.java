package com.project.concurrency.model.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO para representar una lista de concurrencias.
 * Contiene una colección de objetos {@link ConcurrenceDto}.
 */
@Data
public class ConcurrenceListDto {

    /**
     * Lista de concurrencias.
     */
    private List<ConcurrenceDto> items;
}

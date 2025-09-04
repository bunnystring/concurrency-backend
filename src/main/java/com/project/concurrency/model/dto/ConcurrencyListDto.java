package com.project.concurrency.model.dto;

import lombok.Data;
import java.util.List;

/**
 * DTO para representar una lista de concurrencias.
 * Contiene una colección de objetos {@link ConcurrencyDto}.
 */
@Data
public class ConcurrencyListDto {

    /**
     * Lista de concurrencias.
     */
    private List<ConcurrencyDto> items;
}

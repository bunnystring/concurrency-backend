package com.project.concurrency.model.dto;

import lombok.Data;
import java.util.UUID;

/**
 * DTO para representar la información de una concurrencia.
 * Incluye el identificador único, nombre y estado.
 */
@Data
public class ConcurrencyDto {

    /**
     * Identificador único de la concurrencia.
     */
    private UUID id;


    /**
     * Nombre de la concurrencia.
     */
    private String name;

    /**
     * Estado actual de la concurrencia.
     */
    private String status;
}

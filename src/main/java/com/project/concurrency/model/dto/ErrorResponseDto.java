package com.project.concurrency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Objeto de transferencia para respuestas de error.
 * Incluye marca de tiempo, código de error y mensaje descriptivo.
 */
@Getter
@AllArgsConstructor
@ToString
public class ErrorResponseDto {

    /**
     * Marca de tiempo en la que ocurrió el error.
     */
    private final LocalDateTime timestamp;

    /**
     * Código identificador del error.
     */
    private final String errorCode;

    /**
     * Mensaje descriptivo del error.
     */
    private final String message;
}

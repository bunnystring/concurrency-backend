package com.project.concurrency.exception;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Excepción personalizada para errores relacionados con concurrencia en la aplicación.
 * Incluye marca de tiempo, código de error y mensaje descriptivo.
 */
@Getter
@ToString
public class ConcurrencyException extends RuntimeException {
    private final LocalDateTime timestamp;
    private final String errorCode;
    private final String message;

    /**
     * Constructor completo para la excepción de concurrencia.
     * @param timestamp Marca de tiempo del error.
     * @param errorCode Código de error identificador.
     * @param message Mensaje descriptivo del error.
     */
    public ConcurrencyException(LocalDateTime timestamp, String errorCode, String message) {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
    }
}

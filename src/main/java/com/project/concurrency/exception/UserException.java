package com.project.concurrency.exception;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Excepción personalizada para errores relacionados con la entidad User.
 * Incluye marca de tiempo, codigo de error y mensaje descriptivo.
 */
@Getter
@ToString
public class UserException extends RuntimeException{

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

    /**
     * Constructor de la excepción personalizada de usuario.
     * @param errorCode Código de error identificador.
     * @param message Mensaje descriptivo del error.
     */
    public UserException(LocalDateTime timestamp, String errorCode, String message) {
        super(message);
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.message = message;
    }


}

package com.project.concurrency.exception;

import com.project.concurrency.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Manejador global de excepciones para la aplicación.
 * Proporciona respuestas personalizadas ante errores no controlados.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja todas las excepciones no específicas.
     * @param ex Excepción lanzada.
     * @return Respuesta con mensaje de error y código 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    /**
     * Maneja todas las excepciones de tipo UserException.
     * @param ex Excepción personalizada de usuario.
     * @return Respuesta con detalle del error y codigo Http.
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponseDto> handleUserException(UserException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ex.getTimestamp(),
                ex.getErrorCode(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * Maneja excepciones de concurrencia.
     * @param ex Excepción personalizada de concurrencia.
     * @return Respuesta con detalle del error y código 409.
     */
    @ExceptionHandler(ConcurrencyException.class)
    public ResponseEntity<ErrorResponseDto> handleConcurrencyException(ConcurrencyException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                ex.getTimestamp(),
                ex.getErrorCode(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}

package com.project.concurrency.model.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO para la respuesta de autenticación.
 * Contiene el token JWT y los datos básicos del usuario autenticado.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRs {
    /**
     * Token JWT generado tras la autenticación.
     */
    private String token;

    /**
     * Nombre de usuario autenticado.
     */
    private String username;

    /**
     * Correo electrónico del usuario autenticado.
     */
    private String email;

    /**
     * Nombre completo del usuario autenticado.
     */
    private String fullname;
}

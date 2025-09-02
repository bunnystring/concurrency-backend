package com.project.concurrency.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la solicitud de autenticación de usuario.
 * Contiene los campos necesarios para el login: nombre de usuario y contraseña.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRq {

    /**
     * Nombre de usuario para la autenticación.
     * No puede estar vacío y debe tener entre 4 y 50 caracteres.
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres")
    private String username;

    /**
     * Contraseña para la autenticación.
     * No puede estar vacía y debe tener entre 6 y 128 caracteres.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 128, message = "La contraseña debe tener entre 6 y 128 caracteres")
    private String password;
}

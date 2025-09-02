package com.project.concurrency.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la solicitud de registro de usuario.
 * Incluye los campos necesarios para crear una nueva cuenta de usuario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRq {

    /**
     * Nombre de usuario para el registro.
     * No puede estar vacío y debe tener entre 4 y 50 caracteres.
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres")
    private String username;

    /**
     * Contraseña para el registro.
     * No puede estar vacía y debe tener entre 6 y 128 caracteres.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 128, message = "La contraseña debe tener entre 6 y 128 caracteres")
    private String password;

    /**
     * Correo electrónico del usuario.
     * No puede estar vacío y debe tener formato válido.
     */
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    private String email;

    /**
     * Nombre completo del usuario.
     * No puede estar vacío y debe tener entre 2 y 100 caracteres.
     */
    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre completo debe tener entre 2 y 100 caracteres")
    private String fullname;
}
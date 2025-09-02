package com.project.concurrency.services;

import com.project.concurrency.model.dto.AuthRq;
import com.project.concurrency.model.dto.AuthRs;
import com.project.concurrency.model.dto.RegisterRq;

/**
 * Servicio para operaciones de autenticación de usuarios.
 * Proporciona métodos para iniciar sesión y registrar nuevos usuarios en el sistema.
 */
public interface AuthService {

    /**
     * Autentica a un usuario según las credenciales proporcionadas.
     *
     * @param rq Objeto DTO con los datos de autenticación (usuario y contraseña).
     * @return AuthRs DTO con el token JWT y los datos básicos del usuario autenticado.
     */
    AuthRs login(AuthRq rq);

    /**
     * Registra un nuevo usuario en el sistema y lo autentica automáticamente.
     *
     * @param rq Objeto DTO con los datos de registro (usuario, correo, nombre completo y contraseña).
     * @return AuthRs DTO con el token JWT y los datos básicos del usuario registrado/autenticado.
     */
    AuthRs register(RegisterRq rq);

}

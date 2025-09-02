package com.project.concurrency.controller;

import com.project.concurrency.model.dto.AuthRq;
import com.project.concurrency.model.dto.AuthRs;
import com.project.concurrency.model.dto.RegisterRq;
import com.project.concurrency.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para operaciones de autenticación de usuarios.
 * Expone endpoints para iniciar sesión y registrar nuevos usuarios.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor que recibe el servicio de autenticación.
     *
     * @param authService Servicio encargado de la lógica de autenticación y registro.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint para iniciar sesión de un usuario.
     *
     * @param rq DTO con las credenciales de autenticación (usuario y contraseña).
     * @return ResponseEntity con AuthRs que contiene el token JWT y datos del usuario autenticado.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthRs> login(@Valid @RequestBody AuthRq rq){
        return ResponseEntity.ok(authService.login(rq));
    }

    /**
     * Endpoint para registrar un nuevo usuario y devolver autenticación inmediata.
     *
     * @param rq DTO con los datos de registro (usuario, correo, nombre completo y contraseña).
     * @return ResponseEntity con AuthRs que contiene el token JWT y datos del usuario registrado/autenticado.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthRs> register(@Valid @RequestBody RegisterRq rq){
        return ResponseEntity.ok(authService.register(rq));
    }
}

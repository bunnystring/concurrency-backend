package com.project.concurrency.services.impl;

import com.project.concurrency.entity.User;
import com.project.concurrency.model.dto.AuthRq;
import com.project.concurrency.model.dto.AuthRs;
import com.project.concurrency.model.dto.RegisterRq;
import com.project.concurrency.repository.UserRepository;
import com.project.concurrency.security.JwtService;
import com.project.concurrency.security.UserDetailsServiceImpl;
import com.project.concurrency.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación del servicio de autenticación.
 * Provee lógica para login y registro de usuarios.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtService jwtService, UserDetailsServiceImpl userDetailsService,
                           UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Autentica un usuario y genera un token JWT.
     *
     * @param rq DTO con credenciales del usuario.
     * @return DTO con token y datos del usuario autenticado.
     */
    @Override
    public AuthRs login(AuthRq rq) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(rq.getUsername(), rq.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(rq.getUsername());
        Optional<User> userOpt = userRepository.findByUsername(rq.getUsername());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        User user = userOpt.get();
        String token = jwtService.generateToken(userDetails);

        return new AuthRs(token, user.getUsername(), user.getEmail(), user.getFullName());
    }

    /**
     * Registra un nuevo usuario y lo autentica automáticamente (login automático post-registro).
     *
     * @param rq DTO con datos de registro.
     * @return DTO con token y datos del usuario autenticado.
     */
    @Override
    public AuthRs register(RegisterRq rq) {
        if (userRepository.findByUsername(rq.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe.");
        }
        if (userRepository.findByEmail(rq.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        User user = new User();
        user.setUsername(rq.getUsername());
        user.setPassword(passwordEncoder.encode(rq.getPassword()));
        user.setEmail(rq.getEmail());
        user.setFullName(rq.getFullname());
        userRepository.save(user);

        AuthRq authRq = new AuthRq(rq.getUsername(), rq.getPassword());
        return login(authRq);
    }
}

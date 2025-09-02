package com.project.concurrency.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean para el encoder de contraseñas.
     * Utiliza BCrypt para el hash seguro de contraseñas.
     *
     * @return PasswordEncoder con algoritmo BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean para el filtro de autenticación JWT.
     * Este filtro intercepta las peticiones para validar el token JWT.
     *
     * @param jwtService Servicio para operaciones con JWT.
     * @param userDetailsService Servicio para cargar los detalles de usuario.
     * @return Instancia de JwtAuthenticationFilter.
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService){
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    /**
     * Bean para el AuthenticationManager.
     * Permite realizar autenticaciones programáticas en los servicios.
     *
     * @param authenticationConfiguration Configuración de autenticación.
     * @return AuthenticationManager configurado.
     * @throws Exception en caso de error de configuración.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean para la cadena de filtros de seguridad.
     * Configura la protección CSRF, los endpoints públicos, el manejo de sesión
     * sin estado y registra el filtro JWT antes del filtro estándar de autenticación.
     *
     * @param http Configuración HttpSecurity.
     * @param jwtAuthenticationFilter Filtro de autenticación JWT.
     * @return SecurityFilterChain configurada.
     * @throws Exception en caso de error de configuración.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/login", "/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

package com.project.concurrency.repository;

import com.project.concurrency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio para la entidad {@link User}.
 * Proporciona métodos de acceso y consulta a la tabla de usuarios.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Busca un usuario por su nombre de usuario exacto.
     *
     * @param username
     * @return un Optional con el usuario encontrado, o vacio si no existe.
     */
    Optional<User> findByUsername(String username);

    /**
     * Busca usuarios cuyo nombres de usuario contenga el fragmento especificado.
     *
     * @param fragment
     * @return lista de usuarios que contienen el fragmento en su nombre de usuario.
     */
    List<User> findByUsernameContains(String fragment);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email El correo electrónico a buscar.
     * @return Un Optional que contiene el usuario si se encuentra, o vacío si no existe.
     */
    Optional<User> findByEmail(String email);
}

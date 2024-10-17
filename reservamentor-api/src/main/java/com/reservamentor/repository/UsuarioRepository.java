package com.reservamentor.repository;

import com.reservamentor.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);

    Boolean existsByNombreAndApellido(String nombre, String apellido);

    Boolean existsByNombreAndApellidoAndIdNot(String nombre, String apellido, Integer id);
}
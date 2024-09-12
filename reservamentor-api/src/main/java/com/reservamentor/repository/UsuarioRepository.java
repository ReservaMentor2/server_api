package com.reservamentor.repository;

import com.reservamentor.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByCorreo(String correo);
}
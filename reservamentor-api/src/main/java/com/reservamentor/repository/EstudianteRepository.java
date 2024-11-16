package com.reservamentor.repository;

import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Estudiante findByUsuarioid(Usuario usuario);

}

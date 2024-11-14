package com.reservamentor.repository;

import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Favorito;
import com.reservamentor.model.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByEstudianteid(Estudiante estudianteid);
    boolean existsByEstudianteidAndMentorid(Estudiante estudianteid, Mentor mentorid);
    Favorito findByEstudianteidAndMentorid(Estudiante estudianteid, Mentor mentorid);

}

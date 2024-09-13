package com.reservamentor.repository;

import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentorValoracionRepository extends JpaRepository<Valoracion, Integer> {

    @Query(value = "SELECT val FROM Valoracion val WHERE val.mentorid = :mentor")
    List<Valoracion> findValoracionByMentor(@Param("mentor") Mentor mentor);

    @Query("SELECT val FROM Valoracion val WHERE val.estrellas = :estrellas")
    List<Valoracion> findValoracionesByEstrellas(@Param("estrellas") Integer estrellas);

    @Query("SELECT AVG(val.estrellas) FROM Valoracion val WHERE val.mentorid = :mentor")
    Double calculateAverageRating(@Param("mentor") Mentor mentor);
}

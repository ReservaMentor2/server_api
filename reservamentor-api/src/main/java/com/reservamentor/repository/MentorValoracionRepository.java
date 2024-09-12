package com.reservamentor.repository;

import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentorValoracionRepository extends JpaRepository<Valoracion, Integer> {

    @Query(value = "SELECT val FROM Valoracion val WHERE val.mentorid = :mentorId")
    List<Valoracion> findValoracionByMentor(@Param("mentorId") Mentor mentor);
}

package com.reservamentor.repository;


import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.model.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

    @Query("SELECT new com.reservamentor.dto.InformacionMentorDTO(a.id, u.nombre, u.apellido, m.tarifahora, m.biografia)  FROM AsignaturaMentor am " +
            "LEFT JOIN am.mentorid m " +
            "LEFT JOIN am.asignaturaid a " +
            "LEFT JOIN m.usuario u " +
            "WHERE a.id = :asignaturaId")
    List<InformacionMentorDTO> findMentoresByAsignaturaId(@Param("asignaturaId") Integer asignaturaId);

}

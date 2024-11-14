package com.reservamentor.repository;

import com.reservamentor.model.entity.Disponibilidad;
import com.reservamentor.model.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Integer> {
    @Query("SELECT DISTINCT d.mentor FROM Disponibilidad d WHERE d.dia = :dia")
    List<Mentor> findMentorsByDia(@Param("dia") String dia);

    @Query("SELECT d.mentor FROM Disponibilidad d WHERE d.horainicio <= :horaFin AND d.horafin >= :horaInicio")
    List<Mentor> findMentorsByHora(@Param("horaInicio") LocalTime horaInicio, @Param("horaFin") LocalTime horaFin);

    @Query("SELECT DISTINCT d.mentor FROM Disponibilidad d WHERE d.dia = :dia AND d.horainicio <= :horaFin AND d.horafin >= :horaInicio")
    List<Mentor> findMentorsByDiaAndHora(@Param("dia") String dia, @Param("horaInicio") LocalTime horaInicio, @Param("horaFin") LocalTime horaFin);

    // Nuevo método necesario para los cursos cortos
    @Query("SELECT d FROM Disponibilidad d WHERE d.mentor.id = :mentorId")
    List<Disponibilidad> findByMentorId(@Param("mentorId") Integer mentorId);
}

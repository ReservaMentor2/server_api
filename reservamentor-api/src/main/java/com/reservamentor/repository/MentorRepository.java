package com.reservamentor.repository;

import com.reservamentor.model.entity.Mentor;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentorRepository extends JpaRepository<Mentor, Integer> {
  @Query("SELECT m FROM Mentor m JOIN FETCH m.usuarioId") List<Mentor> findAll();

  @Query("SELECT m FROM Mentor m ORDER BY m.valoracionpromedio DESC")
  List<Mentor> findAllOrderByValoracionpromedio();
  List<Mentor> findMentorById(Integer mentorId);

  @Query("SELECT m FROM Mentor m "
         + "WHERE m.tarifahora <= :tarifaMaxima "
         + "AND m.valoracionpromedio >= :valoracionMinima "
         +
         "AND EXISTS (SELECT d FROM Disponibilidad d WHERE d.mentor.id = m.id "
         + "AND d.horainicio <= :horaFinPreferida AND d.horafin >= " +
           ":horaInicioPreferida)")
  List<Mentor>
  findMentoresRecomendados(
      @Param("tarifaMaxima") Integer tarifaMaxima,
      @Param("valoracionMinima") Double valoracionMinima,
      @Param("horaInicioPreferida") LocalTime horaInicioPreferida,
      @Param("horaFinPreferida") LocalTime horaFinPreferida);

  Optional<Mentor> findByUsuario_Id(Integer id);
}

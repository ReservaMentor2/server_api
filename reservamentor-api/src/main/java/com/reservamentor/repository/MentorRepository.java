package com.reservamentor.repository;

import com.reservamentor.model.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {
    @Query("SELECT m FROM Mentor m ORDER BY m.valoracionpromedio DESC")
    List<Mentor> findAllOrderByValoracionpromedio();
  
   List<Mentor> findMentorById(Integer mentorId);

}
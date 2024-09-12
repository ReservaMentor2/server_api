package com.reservamentor.repository;

import com.reservamentor.model.entity.Sesionmentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionMentoriaRepository extends JpaRepository<Sesionmentoria, Integer> {
}

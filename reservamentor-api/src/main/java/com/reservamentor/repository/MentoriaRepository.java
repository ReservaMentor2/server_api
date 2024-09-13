package com.reservamentor.repository;

import com.reservamentor.model.entity.Sesionmentoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoriaRepository extends JpaRepository<Sesionmentoria, Integer>{
}
package com.reservamentor.repository;


import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Sesionmentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SesionmentoriaRepository extends JpaRepository<Sesionmentoria, Integer> {

}
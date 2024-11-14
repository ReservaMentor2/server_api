package com.reservamentor.repository;

import com.reservamentor.model.entity.OportunidadLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OportunidadLaboralRepository extends JpaRepository<OportunidadLaboral, Integer> {

}

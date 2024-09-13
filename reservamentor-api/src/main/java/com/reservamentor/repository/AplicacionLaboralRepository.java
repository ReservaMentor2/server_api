package com.reservamentor.repository;

import com.reservamentor.model.entity.AplicacionLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AplicacionLaboralRepository extends JpaRepository<AplicacionLaboral, Integer> {

    List<AplicacionLaboral> findByCandidato(String candidato);

    List<AplicacionLaboral> findByOportunidadLaboralId(Integer oportunidadId);
}

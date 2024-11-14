package com.reservamentor.repository;

import com.reservamentor.model.entity.AsistenciaEvento;
import com.reservamentor.model.entity.AsistenciaEventoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaEventoRepository extends JpaRepository<AsistenciaEvento, AsistenciaEventoId> {
}

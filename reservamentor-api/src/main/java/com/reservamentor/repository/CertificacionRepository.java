package com.reservamentor.repository;

import com.reservamentor.model.entity.Certificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificacionRepository extends JpaRepository<Certificacion, Integer> {
    List<Certificacion> findByReconocidainternacionalmente(boolean reconocida);
}

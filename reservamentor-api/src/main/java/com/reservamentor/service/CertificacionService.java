package com.reservamentor.service;

import com.reservamentor.model.entity.Certificacion;
import java.util.List;
import java.util.Optional;

public interface CertificacionService {
    List<Certificacion> getAllCertificaciones();
    Optional<Certificacion> getCertificacionById(Integer id);
    List<Certificacion> getCertificacionesReconocidas();
}

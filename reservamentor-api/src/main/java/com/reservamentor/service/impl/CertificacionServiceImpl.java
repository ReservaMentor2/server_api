package com.reservamentor.service.impl;

import com.reservamentor.model.entity.Certificacion;
import com.reservamentor.repository.CertificacionRepository;
import com.reservamentor.service.CertificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificacionServiceImpl implements CertificacionService {

    @Autowired
    private CertificacionRepository certificacionRepository;

    @Override
    public List<Certificacion> getAllCertificaciones() {
        return certificacionRepository.findAll();
    }

    @Override
    public Optional<Certificacion> getCertificacionById(Integer id) {
        return certificacionRepository.findById(id);
    }

    public List<Certificacion> getCertificacionesReconocidas() {
        return certificacionRepository.findByReconocidainternacionalmente(true);
    }

}

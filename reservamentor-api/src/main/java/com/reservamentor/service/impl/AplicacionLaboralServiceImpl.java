package com.reservamentor.service.impl;

import com.reservamentor.model.entity.AplicacionLaboral;
import com.reservamentor.repository.AplicacionLaboralRepository;
import com.reservamentor.service.AplicacionLaboralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplicacionLaboralServiceImpl implements AplicacionLaboralService {

    private final AplicacionLaboralRepository aplicacionLaboralRepository;

    public AplicacionLaboralServiceImpl(AplicacionLaboralRepository aplicacionLaboralRepository) {
        this.aplicacionLaboralRepository = aplicacionLaboralRepository;
    }

    @Override
    public List<AplicacionLaboral> getAllAplicaciones() {
        return aplicacionLaboralRepository.findAll();
    }

    @Override
    public AplicacionLaboral getAplicacionById(Integer id) {
        return aplicacionLaboralRepository.findById(id).orElse(null);
    }

    @Override
    public AplicacionLaboral createAplicacion(AplicacionLaboral aplicacionLaboral) {
        return aplicacionLaboralRepository.save(aplicacionLaboral);
    }

    @Override
    public AplicacionLaboral updateAplicacion(Integer id, AplicacionLaboral aplicacionLaboral) {
        if (aplicacionLaboralRepository.existsById(id)) {
            aplicacionLaboral.setId(id);
            return aplicacionLaboralRepository.save(aplicacionLaboral);
        }
        return null;
    }

    @Override
    public boolean deleteAplicacion(Integer id) {
        if (aplicacionLaboralRepository.existsById(id)) {
            aplicacionLaboralRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<AplicacionLaboral> getAplicacionesByCandidato(String candidato) {
        return aplicacionLaboralRepository.findByCandidato(candidato);
    }

    @Override
    public List<AplicacionLaboral> getAplicacionesByOportunidad(Integer oportunidadId) {
        return aplicacionLaboralRepository.findByOportunidadLaboralId(oportunidadId);
    }
}

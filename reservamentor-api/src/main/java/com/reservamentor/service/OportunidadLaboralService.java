package com.reservamentor.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.reservamentor.model.entity.OportunidadLaboral;
import com.reservamentor.repository.OportunidadLaboralRepository;

@Service
public class OportunidadLaboralService {

    private final OportunidadLaboralRepository oportunidadLaboralRepository;

    public OportunidadLaboralService(OportunidadLaboralRepository oportunidadLaboralRepository) {
        this.oportunidadLaboralRepository = oportunidadLaboralRepository;
    }

    public List<OportunidadLaboral> getAllOportunidades() {
        return oportunidadLaboralRepository.findAll();
    }

    public OportunidadLaboral getOportunidadById(Integer id) {
        return oportunidadLaboralRepository.findById(id).orElse(null);
    }

    public OportunidadLaboral createOportunidad(OportunidadLaboral oportunidad) {
        return oportunidadLaboralRepository.save(oportunidad);
    }

    public OportunidadLaboral updateOportunidad(Integer id, OportunidadLaboral updatedOportunidad) {
        if (oportunidadLaboralRepository.existsById(id)) {
            updatedOportunidad.setOportunidadID(id);
            return oportunidadLaboralRepository.save(updatedOportunidad);
        }
        return null;
    }

    public boolean deleteOportunidad(Integer id) {
        if (oportunidadLaboralRepository.existsById(id)) {
            oportunidadLaboralRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.reservamentor.service.impl;

import com.reservamentor.model.entity.OportunidadLaboral;
import com.reservamentor.repository.OportunidadLaboralRepository;
import com.reservamentor.service.OportunidadLaboralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OportunidadLaboralServiceImpl implements OportunidadLaboralService {

    private final OportunidadLaboralRepository oportunidadLaboralRepository;

    public OportunidadLaboralServiceImpl(OportunidadLaboralRepository oportunidadLaboralRepository) {
        this.oportunidadLaboralRepository = oportunidadLaboralRepository;
    }

    @Override
    public List<OportunidadLaboral> getAllOportunidades() {
        return oportunidadLaboralRepository.findAll();
    }
<<<<<<< HEAD

    @Override
    public OportunidadLaboral getOportunidadLaboralById(Integer id) {
        return oportunidadLaboralRepository.findById(id).orElse(null);
    }

    @Override
    public OportunidadLaboral createOportunidadLaboral(OportunidadLaboral oportunidadLaboral) {
        return oportunidadLaboralRepository.save(oportunidadLaboral);
    }

    @Override
    public OportunidadLaboral updateOportunidadLaboral(Integer id, OportunidadLaboral oportunidadLaboral) {
        if (oportunidadLaboralRepository.existsById(id)) {
            oportunidadLaboral.setId(id); // Asegura que actualices la oportunidad correcta
            return oportunidadLaboralRepository.save(oportunidadLaboral);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteOportunidadLaboral(Integer id) {
        if (oportunidadLaboralRepository.existsById(id)) {
            oportunidadLaboralRepository.deleteById(id);
            return true;
        }
        return false;
    }
=======
>>>>>>> 477368f6a6b49f8c098c0968217bca63362aa5c0
}

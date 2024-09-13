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
}

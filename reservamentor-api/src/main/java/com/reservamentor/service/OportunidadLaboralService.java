package com.reservamentor.service;

import com.reservamentor.model.entity.OportunidadLaboral;

import java.util.List;

public interface OportunidadLaboralService {

    List<OportunidadLaboral> getAllOportunidades();

    OportunidadLaboral getOportunidadLaboralById(Integer id);

    OportunidadLaboral createOportunidadLaboral(OportunidadLaboral oportunidadLaboral);

    OportunidadLaboral updateOportunidadLaboral(Integer id, OportunidadLaboral oportunidadLaboral);

    boolean deleteOportunidadLaboral(Integer id);
}

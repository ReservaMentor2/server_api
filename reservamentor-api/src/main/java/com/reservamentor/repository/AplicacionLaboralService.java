package com.reservamentor.service;

import com.reservamentor.model.entity.AplicacionLaboral;

import java.util.List;

public interface AplicacionLaboralService {

    List<AplicacionLaboral> getAllAplicaciones();

    AplicacionLaboral getAplicacionById(Integer id);

    AplicacionLaboral createAplicacion(AplicacionLaboral aplicacionLaboral);

    AplicacionLaboral updateAplicacion(Integer id, AplicacionLaboral aplicacionLaboral);

    boolean deleteAplicacion(Integer id);

    List<AplicacionLaboral> getAplicacionesByCandidato(String candidato);

    List<AplicacionLaboral> getAplicacionesByOportunidad(Integer oportunidadId);
}

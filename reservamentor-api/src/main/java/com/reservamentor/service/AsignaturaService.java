package com.reservamentor.service;

import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.model.entity.Asignatura;
import com.reservamentor.model.entity.Mentor;

import java.util.List;

public interface AsignaturaService {
    List<Asignatura> getAllAsignaturas();

    List<InformacionMentorDTO> getMentoresByAsignaturaId(Integer id);

}

package com.reservamentor.service.impl;

import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.model.entity.Asignatura;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.AsignaturaRepository;
import com.reservamentor.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignaturaServiceImpl implements AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public List<InformacionMentorDTO> getMentoresByAsignaturaId(Integer id) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asignatura not found"));
        return asignaturaRepository.findMentoresByAsignaturaId(asignatura.getId());
    }

}

package com.reservamentor.service.impl;

import com.reservamentor.dto.CursoCortoDTO;
import com.reservamentor.dto.MentorDisponibilidadDTO;
import com.reservamentor.model.entity.Asignatura;
import com.reservamentor.repository.AsignaturaRepository;
import com.reservamentor.repository.DisponibilidadRepository;
import com.reservamentor.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {


    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<CursoCortoDTO> obtenerCursosCortosPorMentor(Integer mentorId) {
        // Obtener la disponibilidad del mentor
        List<MentorDisponibilidadDTO> disponibilidades = disponibilidadRepository.findByMentorId(mentorId)
                .stream()
                .map(disponibilidad -> {
                    MentorDisponibilidadDTO dto = new MentorDisponibilidadDTO();
                    dto.setDia(disponibilidad.getDia());
                    dto.setHoraInicio(disponibilidad.getHorainicio());
                    dto.setHoraFin(disponibilidad.getHorafin());
                    dto.setMentorId(mentorId);
                    dto.setMentorNombre(disponibilidad.getMentor().getUsuarioId().getNombre());
                    return dto;
                }).collect(Collectors.toList());

        // Obtener asignaturas
        List<Asignatura> asignaturas = asignaturaRepository.findAll();

        // Combinar asignaturas con disponibilidad
        return asignaturas.stream().map(asignatura -> {
            CursoCortoDTO curso = new CursoCortoDTO();
            curso.setNombreCurso(asignatura.getNombre());
            curso.setDescripcionCurso(asignatura.getDescripcion());
            curso.setDisponibilidad(disponibilidades.isEmpty() ? null : disponibilidades.get(0));
            return curso;
        }).collect(Collectors.toList());
    }

}

package com.reservamentor.service;

import com.reservamentor.dto.CursoCortoDTO;
import com.reservamentor.dto.MentorDisponibilidadDTO;
import com.reservamentor.model.entity.Asignatura;
import com.reservamentor.repository.AsignaturaRepository;
import com.reservamentor.repository.DisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface CursoService {

    public List<CursoCortoDTO> obtenerCursosCortosPorMentor(Integer mentorId);

}

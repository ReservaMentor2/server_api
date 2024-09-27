package com.reservamentor.service.impl;

import com.reservamentor.dto.ValoracionDTO;
import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Valoracion;
import com.reservamentor.repository.EstudianteRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.repository.ValoracionRepository;
import com.reservamentor.service.AdminValoracionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminValoracionServiceImpl implements AdminValoracionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Override
    public ValoracionDTO crearValoracion(ValoracionDTO valoracionDTO) {
        Estudiante estudiante = estudianteRepository.findById(valoracionDTO.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Mentor mentor = mentorRepository.findById(valoracionDTO.getMentorId())
                .orElseThrow(() -> new RuntimeException("Mentor no encontrado"));

        Valoracion valoracion = new Valoracion();
        valoracion.setId(valoracionDTO.getId());
        valoracion.setEstudiante(estudiante);
        valoracion.setMentor(mentor);
        valoracion.setComentario(valoracionDTO.getComentario());
        valoracion.setEstrellas(valoracionDTO.getEstrellas());
        valoracion.setValoracionDeMentor(valoracionDTO.getValoracionDeMentor());

        valoracionRepository.save(valoracion);

        return valoracionDTO;
    }
}
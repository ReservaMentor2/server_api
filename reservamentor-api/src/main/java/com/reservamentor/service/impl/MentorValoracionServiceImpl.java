package com.reservamentor.service.impl;

import com.reservamentor.dto.ValoracionDTO;
import com.reservamentor.exception.BadRequestException;
import com.reservamentor.exception.MentorNotFound;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.mapper.ValoracionMapper;
import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Valoracion;
import com.reservamentor.repository.EstudianteRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.repository.MentorValoracionRepository;
import com.reservamentor.service.MentorValoracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorValoracionServiceImpl implements MentorValoracionService {

    private final MentorRepository mentorRepository;
    private final MentorValoracionRepository mentorValoracionRepository;
    private final EstudianteRepository estudianteRepository;
    private final ValoracionMapper valoracionMapper;

    @Override
    public List<Valoracion> findValoracionesByMentor(Integer mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(
        () -> new MentorNotFound("El mentor con ID " + mentorId + " no fue encontrado")
        );
        return mentorValoracionRepository.findValoracionByMentor(mentor);
    }

    @Override
    public List<Valoracion> filterValoracionesByEstrellas(Integer estrellas) {
        return mentorValoracionRepository.findValoracionesByEstrellas(estrellas);
    }

    @Override
    public Double calculateAverageRating(Integer mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(
                () -> new MentorNotFound("El mentor con ID " + mentorId + " no fue encontrado")
        );
        return mentorValoracionRepository.calculateAverageRating(mentor);
    }

    @Override
    public Valoracion createValoracionMentor(Integer estudianteId, Integer mentorId, ValoracionDTO valoracionDTO) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(
                () -> new MentorNotFound("El mentor con ID " + mentorId + " no fue encontrado")
        );

        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElseThrow(
                () -> new MentorNotFound("El mentor con ID " + estudianteId + " no fue encontrado")
        );


        if (mentorValoracionRepository.existsByMentoridAndEstudianteid(mentor, estudiante)) {
            throw new BadRequestException("Ya existe un comentario con el mentor y estudiante");
        }

        Valoracion valoracion = valoracionMapper.toEntity(valoracionDTO);

        System.out.println(valoracion.getEstrellas());
        System.out.println(valoracion.getComentario());

        valoracion.setEstudianteid(estudiante);
        valoracion.setMentorid(mentor);

        System.out.println(valoracion);

        valoracion = mentorValoracionRepository.save(valoracion);

        return valoracion;
    }


}

package com.reservamentor.service.impl;

import com.reservamentor.exception.MentorNotFound;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Valoracion;
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
}

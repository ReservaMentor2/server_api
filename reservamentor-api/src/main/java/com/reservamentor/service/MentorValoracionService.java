package com.reservamentor.service;

import com.reservamentor.model.entity.Valoracion;

import java.util.List;

public interface MentorValoracionService {
    List<Valoracion> findValoracionesByMentor(Integer mentorId);
    List<Valoracion> filterValoracionesByEstrellas(Integer estrellas);
    Double calculateAverageRating(Integer mentorId);

}

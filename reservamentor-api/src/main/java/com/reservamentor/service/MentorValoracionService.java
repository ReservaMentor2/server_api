package com.reservamentor.service;

import com.reservamentor.model.entity.Valoracion;

import java.util.List;

public interface MentorValoracionService {
    List<Valoracion> findValoracionesByMentor(Integer mentorId);
}

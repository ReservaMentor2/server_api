package com.reservamentor.service;

import com.reservamentor.model.entity.SesionMentoria;

public interface AdminMentoriaService {
    SesionMentoria create(SesionMentoria sesionmentoria);
    SesionMentoria update(Integer id, SesionMentoria updateSesionMentoria);
    SesionMentoria delete (Integer id);
    SesionMentoria findById(Integer id);
    SesionMentoria reprogramarMentoria(Integer id, SesionMentoria updateSesionMentoria);

}

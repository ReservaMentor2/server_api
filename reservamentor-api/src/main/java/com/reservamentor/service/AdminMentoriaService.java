package com.reservamentor.service;

import com.reservamentor.model.entity.Sesionmentoria;

import java.util.List;

public interface AdminMentoriaService {
    Sesionmentoria create(Sesionmentoria sesionmentoria);
    Sesionmentoria update(Integer id, Sesionmentoria updateSesionMentoria);
    Sesionmentoria delete (Integer id);
    Sesionmentoria findById(Integer id);
    Sesionmentoria reprogramarMentoria(Integer id, Sesionmentoria updateSesionMentoria);

}

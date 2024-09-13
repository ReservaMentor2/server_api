package com.reservamentor.service;


import com.reservamentor.model.entity.Sesionmentoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;

public interface AdminSesionmentoriaService {
    List<Sesionmentoria> getAll();
    Page<Sesionmentoria> paginate(Pageable pageable);
    Sesionmentoria create(Sesionmentoria sesionmentoria);
    Sesionmentoria update(Integer id, Sesionmentoria sesionmentoria);
    void delete(Integer id);
    Sesionmentoria findById(Integer id);
}
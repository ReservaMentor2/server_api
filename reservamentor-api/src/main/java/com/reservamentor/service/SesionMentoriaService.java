package com.reservamentor.service;


import com.reservamentor.model.entity.SesionMentoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SesionMentoriaService {
    List<SesionMentoria> getAll();
    Page<SesionMentoria> paginate(Pageable pageable);
    SesionMentoria create(SesionMentoria sesionmentoria);
    SesionMentoria update(Integer id, SesionMentoria sesionmentoria);
    void delete(Integer id);
    SesionMentoria findById(Integer id);
}
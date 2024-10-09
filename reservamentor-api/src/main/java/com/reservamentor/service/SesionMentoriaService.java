package com.reservamentor.service;

import com.reservamentor.model.entity.SesionMentoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SesionMentoriaService {

    List<SesionMentoria> getAll();
    SesionMentoria searchById(Integer id);

    Page<SesionMentoria> paginate(Pageable pageable);
    SesionMentoria create(SesionMentoria sesionMentoria);
    SesionMentoria update(Integer id, SesionMentoria sesionMentoria);
    void delete(Integer id);
}

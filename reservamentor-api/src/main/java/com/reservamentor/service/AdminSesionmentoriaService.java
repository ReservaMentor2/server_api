package com.reservamentor.service;


import com.reservamentor.dto.SesionmentoriaDTO;
import com.reservamentor.model.entity.Sesionmentoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminSesionmentoriaService {
    List<SesionmentoriaDTO> getAll();
    Page<SesionmentoriaDTO> paginate(Pageable pageable);
    SesionmentoriaDTO create(SesionmentoriaDTO sesionmentoriaDTO);
    SesionmentoriaDTO update(Integer id, SesionmentoriaDTO sesionmentoriaDTO);
    void delete(Integer id);
    SesionmentoriaDTO findById(Integer id);
}
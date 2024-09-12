package com.reservamentor.service;

import com.reservamentor.model.entity.Disponibilidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdminDisponibilidadService {
    List<Disponibilidad> getAll();
    Page<Disponibilidad> paginate(Pageable pageable);
    Disponibilidad create(Disponibilidad mentor);
    Disponibilidad update(Integer id, Disponibilidad mentor);
    void delete(Integer id);
    Disponibilidad findById(Integer id);
}
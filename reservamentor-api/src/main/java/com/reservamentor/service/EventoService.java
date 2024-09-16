package com.reservamentor.service;

import com.reservamentor.model.entity.AsistenciaEvento;
import com.reservamentor.model.entity.Evento;
import com.reservamentor.model.entity.Mentor;

import java.util.List;
import java.util.Optional;

public interface EventoService {
    List<Evento> getAllEventos();
    Optional<Evento> getEventoById(Integer id);
    AsistenciaEvento actualizarAsistencia(Mentor mentor, Evento evento, boolean confirmada);
}

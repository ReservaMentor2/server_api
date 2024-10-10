package com.reservamentor.service;

import com.reservamentor.model.entity.AsistenciaEvento;
import com.reservamentor.model.entity.Evento;
import com.reservamentor.model.entity.Mentor;

import java.util.List;
import java.util.Optional;

public interface EventoService {
    List<Evento> getAllEventos();
    Optional<Evento> getEventoById(Integer id);
    Evento create(Evento evento);
    AsistenciaEvento actualizarAsistencia(Integer mentorId, Integer eventoId, boolean confirmada);
}

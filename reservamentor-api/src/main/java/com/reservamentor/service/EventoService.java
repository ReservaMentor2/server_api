package com.reservamentor.service;

import com.reservamentor.model.entity.Evento;
import com.reservamentor.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Integer id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento createEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento updateEvento(Integer id, Evento evento) {
        if (eventoRepository.existsById(id)) {
            evento.setId(id);
            return eventoRepository.save(evento);
        }
        return null;
    }

    public boolean deleteEvento(Integer id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

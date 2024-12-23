package com.reservamentor.service.impl;

import com.reservamentor.exception.MentorNotFound;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.AsistenciaEvento;
import com.reservamentor.model.entity.Evento;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.AsistenciaEventoId;
import com.reservamentor.repository.AsistenciaEventoRepository;
import com.reservamentor.repository.EventoRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AsistenciaEventoRepository asistenciaEventoRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> getEventoById(Integer id) {
        return eventoRepository.findById(id);
    }

    @Transactional
    @Override
    public Evento create(Evento evento){
        return eventoRepository.save(evento);
    }

    @Override
    public AsistenciaEvento actualizarAsistencia(Integer mentorId, Integer eventoId, boolean confirmada) {

        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(
                () -> new MentorNotFound("El mentor con ID " + mentorId + " no fue encontrado")
        );

        Evento evento = eventoRepository.findById(eventoId).orElseThrow(
                () -> new ResourceNotFoundException("El evento con ID " + eventoId + " no fue encontrado")
        );

        AsistenciaEventoId id = new AsistenciaEventoId();
        id.setMentorid(mentor.getId());
        id.setEventoid(evento.getId());

        AsistenciaEvento asistenciaEvento = asistenciaEventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada"));

        asistenciaEvento.setAsistenciaconfirmada(confirmada);
        return asistenciaEventoRepository.save(asistenciaEvento);
    }
}

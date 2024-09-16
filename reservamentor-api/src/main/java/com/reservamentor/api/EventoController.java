package com.reservamentor.api;

import com.reservamentor.model.entity.Evento;
import com.reservamentor.model.entity.AsistenciaEvento;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.EventoService;
import com.reservamentor.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private MentorService mentorService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        List<Evento> eventos = eventoService.getAllEventos();
        return ResponseEntity.ok(eventos);
    }

    // Obtener un evento por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Confirmar o cancelar asistencia a un evento
    @PutMapping("/{eventoId}/{mentorId}/{confirmada}")
    public ResponseEntity<AsistenciaEvento> actualizarAsistencia(
            @PathVariable Integer eventoId,
            @PathVariable Integer mentorId,
            @PathVariable boolean confirmada) {

        Optional<Evento> eventoOpt = eventoService.getEventoById(eventoId);
        if (eventoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Mentor> mentorOpt = mentorService.getMentorById(mentorId);
        if (mentorOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // Mentor no encontrado
        }

        AsistenciaEvento asistenciaEvento = eventoService.actualizarAsistencia(
                mentorOpt.get(), eventoOpt.get(), confirmada);

        return ResponseEntity.ok(asistenciaEvento);
    }
}

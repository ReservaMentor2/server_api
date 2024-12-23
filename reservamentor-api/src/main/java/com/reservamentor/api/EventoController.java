package com.reservamentor.api;

import com.reservamentor.model.entity.Evento;
import com.reservamentor.model.entity.AsistenciaEvento;
import com.reservamentor.service.EventoService;
import com.reservamentor.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos")
@PreAuthorize("hasAnyRole('MENTOR', 'ADMIN')")
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

    // Crear evento
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento inputEvento){
        Evento newEvento = eventoService.create(inputEvento);
        return new ResponseEntity<Evento>(newEvento, HttpStatus.CREATED);
    }

    // Confirmar o cancelar asistencia a un evento
    @PutMapping("/{eventoId}/{mentorId}/{confirmada}")
    public ResponseEntity<AsistenciaEvento> actualizarAsistencia(
            @PathVariable Integer eventoId,
            @PathVariable Integer mentorId,
            @PathVariable boolean confirmada) {

        AsistenciaEvento asistenciaEvento = eventoService.actualizarAsistencia(
                mentorId, eventoId, confirmada);

        return ResponseEntity.ok(asistenciaEvento);
    }
}

package com.reservamentor.api;

import com.reservamentor.model.entity.Evento;
import com.reservamentor.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // Obtener todos los eventos (posibles oportunidades laborales)
    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    // Obtener un evento específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Evento evento = eventoService.getEventoById(id);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo evento
    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento createdEvento = eventoService.createEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvento);
    }

    // Actualizar un evento existente
    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @RequestBody Evento evento) {
        Evento updatedEvento = eventoService.updateEvento(id, evento);
        return updatedEvento != null ? ResponseEntity.ok(updatedEvento) : ResponseEntity.notFound().build();
    }

    // Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        boolean deleted = eventoService.deleteEvento(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

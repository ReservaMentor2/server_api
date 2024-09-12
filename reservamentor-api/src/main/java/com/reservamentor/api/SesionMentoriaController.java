package com.reservamentor.api;

import com.reservamentor.model.entity.Sesionmentoria;
import com.reservamentor.service.SesionMentoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones-mentoria")
public class SesionMentoriaController {

    private final SesionMentoriaService sesionMentoriaService;

    public SesionMentoriaController(SesionMentoriaService sesionMentoriaService) {
        this.sesionMentoriaService = sesionMentoriaService;
    }

    // Obtener todas las sesiones de mentoría (aplicaciones)
    @GetMapping
    public List<Sesionmentoria> getAllSesionesMentoria() {
        return sesionMentoriaService.getAllSesionesMentoria();
    }

    // Obtener una sesión de mentoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sesionmentoria> getSesionMentoriaById(@PathVariable Integer id) {
        Sesionmentoria sesionMentoria = sesionMentoriaService.getSesionMentoriaById(id);
        return sesionMentoria != null ? ResponseEntity.ok(sesionMentoria) : ResponseEntity.notFound().build();
    }

    // Crear una nueva sesión de mentoría
    @PostMapping
    public ResponseEntity<Sesionmentoria> createSesionMentoria(@RequestBody Sesionmentoria sesionMentoria) {
        Sesionmentoria createdSesionMentoria = sesionMentoriaService.createSesionMentoria(sesionMentoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSesionMentoria);
    }

    // Actualizar una sesión de mentoría
    @PutMapping("/{id}")
    public ResponseEntity<Sesionmentoria> updateSesionMentoria(@PathVariable Integer id, @RequestBody Sesionmentoria sesionMentoria) {
        Sesionmentoria updatedSesionMentoria = sesionMentoriaService.updateSesionMentoria(id, sesionMentoria);
        return updatedSesionMentoria != null ? ResponseEntity.ok(updatedSesionMentoria) : ResponseEntity.notFound().build();
    }

    // Eliminar una sesión de mentoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesionMentoria(@PathVariable Integer id) {
        boolean deleted = sesionMentoriaService.deleteSesionMentoria(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
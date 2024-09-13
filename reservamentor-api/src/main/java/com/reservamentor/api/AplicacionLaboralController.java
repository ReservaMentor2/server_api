package com.reservamentor.api;

import com.reservamentor.model.entity.AplicacionLaboral;
import com.reservamentor.service.AplicacionLaboralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aplicaciones")
public class AplicacionLaboralController {

    private final AplicacionLaboralService aplicacionLaboralService;

    public AplicacionLaboralController(AplicacionLaboralService aplicacionLaboralService) {
        this.aplicacionLaboralService = aplicacionLaboralService;
    }

    // Obtener todas las aplicaciones laborales
    @GetMapping
    public ResponseEntity<List<AplicacionLaboral>> getAllAplicaciones() {
        List<AplicacionLaboral> aplicaciones = aplicacionLaboralService.getAllAplicaciones();
        return ResponseEntity.ok(aplicaciones);
    }

    // Obtener una aplicación por ID
    @GetMapping("/{id}")
    public ResponseEntity<AplicacionLaboral> getAplicacionById(@PathVariable Integer id) {
        AplicacionLaboral aplicacion = aplicacionLaboralService.getAplicacionById(id);
        return aplicacion != null ? ResponseEntity.ok(aplicacion) : ResponseEntity.notFound().build();
    }

    // Crear una nueva aplicación laboral
    @PostMapping
    public ResponseEntity<AplicacionLaboral> createAplicacion(@RequestBody AplicacionLaboral aplicacionLaboral) {
        AplicacionLaboral nuevaAplicacion = aplicacionLaboralService.createAplicacion(aplicacionLaboral);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAplicacion);
    }

    // Actualizar una aplicación laboral
    @PutMapping("/{id}")
    public ResponseEntity<AplicacionLaboral> updateAplicacion(@PathVariable Integer id, @RequestBody AplicacionLaboral aplicacionLaboral) {
        AplicacionLaboral updatedAplicacion = aplicacionLaboralService.updateAplicacion(id, aplicacionLaboral);
        return updatedAplicacion != null ? ResponseEntity.ok(updatedAplicacion) : ResponseEntity.notFound().build();
    }

    // Eliminar una aplicación laboral
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAplicacion(@PathVariable Integer id) {
        boolean deleted = aplicacionLaboralService.deleteAplicacion(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Obtener aplicaciones por candidato
    @GetMapping("/candidato/{candidato}")
    public ResponseEntity<List<AplicacionLaboral>> getAplicacionesByCandidato(@PathVariable String candidato) {
        List<AplicacionLaboral> aplicaciones = aplicacionLaboralService.getAplicacionesByCandidato(candidato);
        return ResponseEntity.ok(aplicaciones);
    }

    // Obtener aplicaciones por oportunidad laboral
    @GetMapping("/oportunidad/{oportunidadId}")
    public ResponseEntity<List<AplicacionLaboral>> getAplicacionesByOportunidad(@PathVariable Integer oportunidadId) {
        List<AplicacionLaboral> aplicaciones = aplicacionLaboralService.getAplicacionesByOportunidad(oportunidadId);
        return ResponseEntity.ok(aplicaciones);
    }
}

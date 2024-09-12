package com.reservamentor.api;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reservamentor.model.entity.AplicacionLaboral;
import com.reservamentor.service.AplicacionLaboralService;

@RestController
@RequestMapping("/api/v1/aplicaciones")
public class AplicacionLaboralController {

    private final AplicacionLaboralService aplicacionLaboralService;

    public AplicacionLaboralController(AplicacionLaboralService aplicacionLaboralService) {
        this.aplicacionLaboralService = aplicacionLaboralService;
    }

    // Obtener todas las aplicaciones laborales
    @GetMapping
    public List<AplicacionLaboral> getAllAplicaciones() {
        return aplicacionLaboralService.getAllAplicaciones();
    }

    // Obtener una aplicación laboral por ID
    @GetMapping("/{id}")
    public ResponseEntity<AplicacionLaboral> getAplicacionById(@PathVariable Integer id) {
        AplicacionLaboral aplicacion = aplicacionLaboralService.getAplicacionById(id);
        return aplicacion != null ? ResponseEntity.ok(aplicacion) : ResponseEntity.notFound().build();
    }

    // Crear una nueva aplicación laboral
    @PostMapping
    public ResponseEntity<AplicacionLaboral> createAplicacion(@RequestBody AplicacionLaboral aplicacion) {
        AplicacionLaboral createdAplicacion = aplicacionLaboralService.createAplicacion(aplicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAplicacion);
    }

    // Actualizar una aplicación laboral existente
    @PutMapping("/{id}")
    public ResponseEntity<AplicacionLaboral> updateAplicacion(@PathVariable Integer id, @RequestBody AplicacionLaboral aplicacion) {
        AplicacionLaboral updatedAplicacion = aplicacionLaboralService.updateAplicacion(id, aplicacion);
        return updatedAplicacion != null ? ResponseEntity.ok(updatedAplicacion) : ResponseEntity.notFound().build();
    }

    // Eliminar una aplicación laboral por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAplicacion(@PathVariable Integer id) {
        boolean deleted = aplicacionLaboralService.deleteAplicacion(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

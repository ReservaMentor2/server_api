package com.reservamentor.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reservamentor.model.entity.OportunidadLaboral;
import com.reservamentor.service.OportunidadLaboralService;

@RestController
@RequestMapping("/api/v1/oportunidades")
public class OportunidadLaboralController {

    private final OportunidadLaboralService oportunidadLaboralService;

    public OportunidadLaboralController(OportunidadLaboralService oportunidadLaboralService) {
        this.oportunidadLaboralService = oportunidadLaboralService;
    }

    // Obtener todas las oportunidades laborales
    @GetMapping
    public List<OportunidadLaboral> getAllOportunidades() {
        return oportunidadLaboralService.getAllOportunidades();
    }

    // Obtener una oportunidad laboral por ID
    @GetMapping("/{id}")
    public ResponseEntity<OportunidadLaboral> getOportunidadById(@PathVariable Integer id) {
        OportunidadLaboral oportunidad = oportunidadLaboralService.getOportunidadById(id);
        return oportunidad != null ? ResponseEntity.ok(oportunidad) : ResponseEntity.notFound().build();
    }

    // Crear una nueva oportunidad laboral
    @PostMapping
    public ResponseEntity<OportunidadLaboral> createOportunidad(@RequestBody OportunidadLaboral oportunidad) {
        OportunidadLaboral createdOportunidad = oportunidadLaboralService.createOportunidad(oportunidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOportunidad);
    }

    // Actualizar una oportunidad laboral existente
    @PutMapping("/{id}")
    public ResponseEntity<OportunidadLaboral> updateOportunidad(@PathVariable Integer id, @RequestBody OportunidadLaboral oportunidad) {
        OportunidadLaboral updatedOportunidad = oportunidadLaboralService.updateOportunidad(id, oportunidad);
        return updatedOportunidad != null ? ResponseEntity.ok(updatedOportunidad) : ResponseEntity.notFound().build();
    }

    // Eliminar una oportunidad laboral por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOportunidad(@PathVariable Integer id) {
        boolean deleted = oportunidadLaboralService.deleteOportunidad(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
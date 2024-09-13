package com.reservamentor.api;

import com.reservamentor.model.entity.OportunidadLaboral;
import com.reservamentor.service.OportunidadLaboralService;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 477368f6a6b49f8c098c0968217bca63362aa5c0

import java.util.List;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadLaboralController {

    private final OportunidadLaboralService oportunidadLaboralService;

    public OportunidadLaboralController(OportunidadLaboralService oportunidadLaboralService) {
        this.oportunidadLaboralService = oportunidadLaboralService;
    }

<<<<<<< HEAD
    // Obtener todas las oportunidades laborales
=======
>>>>>>> 477368f6a6b49f8c098c0968217bca63362aa5c0
    @GetMapping
    public ResponseEntity<List<OportunidadLaboral>> getAllOportunidades() {
        List<OportunidadLaboral> oportunidades = oportunidadLaboralService.getAllOportunidades();
        return ResponseEntity.ok(oportunidades);
    }
<<<<<<< HEAD

    // Obtener una oportunidad laboral por ID
    @GetMapping("/{id}")
    public ResponseEntity<OportunidadLaboral> getOportunidadById(@PathVariable Integer id) {
        OportunidadLaboral oportunidad = oportunidadLaboralService.getOportunidadLaboralById(id);
        return oportunidad != null ? ResponseEntity.ok(oportunidad) : ResponseEntity.notFound().build();
    }

    // Crear una nueva oportunidad laboral
    @PostMapping
    public ResponseEntity<OportunidadLaboral> createOportunidad(@RequestBody OportunidadLaboral oportunidadLaboral) {
        OportunidadLaboral nuevaOportunidad = oportunidadLaboralService.createOportunidadLaboral(oportunidadLaboral);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOportunidad);
    }

    // Actualizar una oportunidad laboral existente
    @PutMapping("/{id}")
    public ResponseEntity<OportunidadLaboral> updateOportunidad(@PathVariable Integer id, @RequestBody OportunidadLaboral oportunidadLaboral) {
        OportunidadLaboral updatedOportunidad = oportunidadLaboralService.updateOportunidadLaboral(id, oportunidadLaboral);
        return updatedOportunidad != null ? ResponseEntity.ok(updatedOportunidad) : ResponseEntity.notFound().build();
    }

    // Eliminar una oportunidad laboral por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOportunidad(@PathVariable Integer id) {
        boolean deleted = oportunidadLaboralService.deleteOportunidadLaboral(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
=======
>>>>>>> 477368f6a6b49f8c098c0968217bca63362aa5c0
}

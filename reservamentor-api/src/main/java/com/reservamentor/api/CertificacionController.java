package com.reservamentor.api;

import com.reservamentor.model.entity.Certificacion;
import com.reservamentor.service.CertificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificaciones")
public class CertificacionController {

    @Autowired
    private CertificacionService certificacionService;

    // Obtener todas las certificaciones
    @GetMapping
    public ResponseEntity<List<Certificacion>> getAllCertificaciones() {
        List<Certificacion> certificaciones = certificacionService.getAllCertificaciones();
        return ResponseEntity.ok(certificaciones);
    }

    // Obtener certificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Certificacion> getCertificacionById(@PathVariable Integer id) {
        Optional<Certificacion> certificacion = certificacionService.getCertificacionById(id);
        return certificacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener certificaciones reconocidas internacionalmente
    @GetMapping("/reconocidas")
    public ResponseEntity<List<Certificacion>> getCertificacionesReconocidas() {
        List<Certificacion> certificaciones = certificacionService.getCertificacionesReconocidas();
        return ResponseEntity.ok(certificaciones);
    }
}

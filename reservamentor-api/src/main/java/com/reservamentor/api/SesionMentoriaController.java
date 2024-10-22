package com.reservamentor.api;

import com.reservamentor.dto.SesionMentoriaFeedbackDTO;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.service.SesionMentoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sesionMentoria")
public class SesionMentoriaController {

    private final SesionMentoriaService sesionMentoriaService;

    // Obtener todas las sesiones de mentoría
    @GetMapping
    public ResponseEntity<List<SesionMentoria>> getAllSesionMentorias() {
        List<SesionMentoria> sesionMentorias = sesionMentoriaService.getAll();
        return new ResponseEntity<>(sesionMentorias, HttpStatus.OK);
    }

    // Crear una nueva sesión de mentoría
    @PutMapping
    public ResponseEntity<SesionMentoria> createSesionMentoria(@RequestBody SesionMentoria sesionMentoria) {
        SesionMentoria sesionMentoria1 = sesionMentoriaService.create(sesionMentoria);
        return new ResponseEntity<>(sesionMentoria1, HttpStatus.CREATED);
    }

    // Actualizar una sesión de mentoría existente
    @PutMapping("/{id}")
    public ResponseEntity<SesionMentoria> updateSesionMentoria(@PathVariable("id") Integer id, @RequestBody SesionMentoria sesionMentoria) {
        SesionMentoria updatedMentor = sesionMentoriaService.update(id, sesionMentoria);
        return new ResponseEntity<>(updatedMentor, HttpStatus.OK);
    }

    // Eliminar la sesión de mentoría por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesionMentoria(@PathVariable("id") Integer id) {
        sesionMentoriaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Buscar una sesión de mentoría por id
    @GetMapping("/{id}")
    public ResponseEntity<SesionMentoria> getSesionMentoriaById(@PathVariable("id") Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaService.searchById(id);
        return new ResponseEntity<>(sesionMentoria, HttpStatus.OK);
    }

    // Obtener las sesiones de mentoría por paginación
    @GetMapping("/page")
    public ResponseEntity<Page<SesionMentoria>> paginateSesionesMentoria(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<SesionMentoria> sesionesMentoria = sesionMentoriaService.paginate(pageable);
        return new ResponseEntity<>(sesionesMentoria, HttpStatus.OK);
    }

    // Agendar una sesión de mentoría con retroalimentación en tiempo real
    @PostMapping("/feedback")
    public ResponseEntity<SesionMentoria> agendarSesionFeedback(@RequestBody SesionMentoriaFeedbackDTO dto) {
        SesionMentoria sesion = sesionMentoriaService.programarSesionConFeedback(dto);
        return ResponseEntity.ok(sesion);
    }
}



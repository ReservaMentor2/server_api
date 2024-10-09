package com.reservamentor.api;

import com.reservamentor.model.entity.Disponibilidad;
import com.reservamentor.service.DisponibilidadService;
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
@RequestMapping("/disponibilidad")
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;

    @GetMapping
    public ResponseEntity<List<Disponibilidad>> getAllDisponibilidades() {
        List<Disponibilidad> disponibilidades = disponibilidadService.getAll();
        return new ResponseEntity<List<Disponibilidad>>(disponibilidades, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Disponibilidad>> paginateDisponibilidades(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<Disponibilidad> disponibilidades = disponibilidadService.paginate(pageable);
        return new ResponseEntity<Page<Disponibilidad>>(disponibilidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidad> getDisponibilidadById(@PathVariable("id") Integer id) {
        Disponibilidad disponibilidad = disponibilidadService.findById(id);
        return new ResponseEntity<Disponibilidad>(disponibilidad, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Disponibilidad> createDisponibilidad(@RequestBody Disponibilidad disponibility) {
        Disponibilidad newDisponibilidad = disponibilidadService.create(disponibility);
        return new ResponseEntity<Disponibilidad>(newDisponibilidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilidad> updateDisponibilidad(@PathVariable("id") Integer id, @RequestBody Disponibilidad disponibility) {
        Disponibilidad updateMentor = disponibilidadService.update(id, disponibility);
        return new ResponseEntity<Disponibilidad>(updateMentor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Disponibilidad> deleteDisponibilidad(@PathVariable("id") Integer id) {
        disponibilidadService.delete(id);
        return new ResponseEntity<Disponibilidad>(HttpStatus.NO_CONTENT);
    }
}
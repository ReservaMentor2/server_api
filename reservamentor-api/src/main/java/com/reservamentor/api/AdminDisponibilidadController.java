package com.reservamentor.api;

import com.reservamentor.model.entity.Disponibilidad;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.AdminDisponibilidadService;
import com.reservamentor.service.AdminMentorService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/disponibilidad")
public class AdminDisponibilidadController {
    private final AdminDisponibilidadService adminDisponibilidadService;

    @GetMapping
    public ResponseEntity<List<Disponibilidad>> getAllDisponibilidades() {
        List<Disponibilidad> disponibilidads = adminDisponibilidadService.getAll();
        return new ResponseEntity<List<Disponibilidad>>(disponibilidads, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Disponibilidad>> paginateDisponibilidades(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<Disponibilidad> disponibilidads = adminDisponibilidadService.paginate(pageable);
        return new ResponseEntity<Page<Disponibilidad>>(disponibilidads, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidad> getDisponibilidadById(@PathVariable("id") Integer id) {
        Disponibilidad disponibility = adminDisponibilidadService.findById(id);
        return new ResponseEntity<Disponibilidad>(disponibility, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Disponibilidad> createDisponibilidad(@RequestBody Disponibilidad disponibility) {
        Disponibilidad newDisponibilidad = adminDisponibilidadService.create(disponibility);
        return new ResponseEntity<Disponibilidad>(newDisponibilidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilidad> updateDisponibilidad(@PathVariable("id") Integer id, @RequestBody Disponibilidad disponibility) {
        Disponibilidad updateMentor = adminDisponibilidadService.update(id, disponibility);
        return new ResponseEntity<Disponibilidad>(updateMentor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Disponibilidad> deleteDisponibilidad(@PathVariable("id") Integer id) {
        adminDisponibilidadService.delete(id);
        return new ResponseEntity<Disponibilidad>(HttpStatus.NO_CONTENT);
    }
}
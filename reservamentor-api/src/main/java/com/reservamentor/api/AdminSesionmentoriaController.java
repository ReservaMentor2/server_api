package com.reservamentor.api;

import com.reservamentor.model.entity.Sesionmentoria;
import com.reservamentor.service.AdminSesionmentoriaService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/sesionmentoria")
public class AdminSesionmentoriaController {
    private final AdminSesionmentoriaService adminSesionmentoriaService;

    @GetMapping
    public ResponseEntity<List<Sesionmentoria>> getAllSesions() {
        List<Sesionmentoria> mentors = adminSesionmentoriaService.getAll();
        return new ResponseEntity<List<Sesionmentoria>>(mentors, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Sesionmentoria>> paginateSesions(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<Sesionmentoria> mentors = adminSesionmentoriaService.paginate(pageable);
        return new ResponseEntity<Page<Sesionmentoria>>(mentors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sesionmentoria> getSesionById(@PathVariable("id") Integer id) {
        Sesionmentoria mentor = adminSesionmentoriaService.findById(id);
        return new ResponseEntity<Sesionmentoria>(mentor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sesionmentoria> createSesion(@RequestBody Sesionmentoria mentor) {
        Sesionmentoria newMentor = adminSesionmentoriaService.create(mentor);
        return new ResponseEntity<Sesionmentoria>(newMentor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sesionmentoria> updateSesion(@PathVariable("id") Integer id, @RequestBody Sesionmentoria mentor) {
        Sesionmentoria updateMentor = adminSesionmentoriaService.update(id, mentor);
        return new ResponseEntity<Sesionmentoria>(updateMentor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sesionmentoria> deleteSesion(@PathVariable("id") Integer id) {
        adminSesionmentoriaService.delete(id);
        return new ResponseEntity<Sesionmentoria>(HttpStatus.NO_CONTENT);
    }
}
package com.reservamentor.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.MentorService;

@RestController
@RequestMapping("/mentors")
public class MentorController {

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    // Obtener todos los mentores
    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorService.getAllMentores();
    }

    // Obtener un mentor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable Integer id) {
        Mentor mentor = mentorService.getMentorById(id);
        return mentor != null ? ResponseEntity.ok(mentor) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo mentor
    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor) {
        Mentor createdMentor = mentorService.createMentor(mentor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMentor);
    }

    // Actualizar un mentor existente
    //@PutMapping("/{id}")
    //public ResponseEntity<Mentor> updateMentor(@PathVariable Integer id, @RequestBody Mentor mentor) {
    //    Mentor updatedMentor = mentorService.updateMentor(id, mentor);
    //    return updatedMentor != null ? ResponseEntity.ok(updatedMentor) : ResponseEntity.notFound().build();
    //}

    // Eliminar un mentor por ID
    //@DeleteMapping("/{id}")
    //public ResponseEntity<Void> deleteMentor(@PathVariable Integer id) {
    //    boolean deleted = mentorService.deleteMentor(id);
    //    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    //}

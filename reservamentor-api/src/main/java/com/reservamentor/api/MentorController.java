package com.reservamentor.api;

import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.MentorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentors")
public class MentorController {

    private final MentorService mentorService;

    // Constructor con inyección de dependencia
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    // Obtener todos los mentores
    @GetMapping
    public ResponseEntity<List<Mentor>> getAllMentors() {
        List<Mentor> mentors = mentorService.getAllMentores();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
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


    @GetMapping("/profile/{id}")
    public ResponseEntity<MentorPerfilDTO> getMentorPerfil(@PathVariable Integer id) {
        try {
            MentorPerfilDTO mentorPerfil = mentorService.getMentorPerfil(id);
            return ResponseEntity.ok(mentorPerfil);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    // Actualizar un mentor existente
    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable Integer id, @RequestBody Mentor mentor) {
        Mentor updatedMentor = mentorService.updateMentor(id, mentor);
        return updatedMentor != null ? ResponseEntity.ok(updatedMentor) : ResponseEntity.notFound().build();
    }

     */

    // Eliminar un mentor por ID
    //@DeleteMapping("/{id}")
    //public ResponseEntity<Void> deleteMentor(@PathVariable Integer id) {
    //    boolean deleted = mentorService.deleteMentor(id);
    //    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    //}
}
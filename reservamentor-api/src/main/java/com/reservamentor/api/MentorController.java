package com.reservamentor.api;

import com.reservamentor.dto.InformacionMentorDTO1;
import com.reservamentor.model.entity.Asignatura;
import com.reservamentor.service.AsignaturaService;

import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.MentorService;

import com.reservamentor.dto.MentorPerfilDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/mentors")
public class MentorController {

    private final MentorService mentorService;
    private final AsignaturaService asignaturaService;

    // Constructor con inyección de dependencia
    public MentorController(MentorService mentorService, AsignaturaService asignaturaService) {
        this.mentorService = mentorService;
        this.asignaturaService = asignaturaService;
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
        Optional<Mentor> mentorOpt = mentorService.getMentorById(id);
        return mentorOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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


    //Obtener mentores por area de conocimento

    //Obtiene todas las areas de conocimiento de la plataforma
    @GetMapping("/asignatura")
    public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
        return new ResponseEntity<>(asignaturas, HttpStatus.OK);
    }

    //Obtiene todos los mentores por el area de conocimiento
    @GetMapping("/asignatura/{id}")
    public ResponseEntity<List<InformacionMentorDTO1>> getMentoresByAssignaturaId(@PathVariable Integer id) {
        List<InformacionMentorDTO1> informacionMentores = asignaturaService.getMentoresByAsignaturaId(id);
        return new ResponseEntity<>(informacionMentores, HttpStatus.OK);
    }

}
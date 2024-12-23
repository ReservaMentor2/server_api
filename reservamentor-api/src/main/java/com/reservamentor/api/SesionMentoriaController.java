package com.reservamentor.api;

import com.reservamentor.dto.CrearSesionMentoriaRequestDTO;
import com.reservamentor.model.entity.*;
import com.reservamentor.repository.AsignaturaRepository;
import com.reservamentor.repository.EstudianteRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.repository.SesionMentoriaRepository;
import com.reservamentor.service.SesionMentoriaService;
import com.reservamentor.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sesionMentoria")
@PreAuthorize("hasAnyRole('ESTUDIANTE', 'ADMIN','MENTOR')")
public class SesionMentoriaController {

    private final SesionMentoriaService sesionMentoriaService;
    private final UsuarioService usuarioService;
    private final EstudianteRepository estudianteRepository;
    private final MentorRepository mentorRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final SesionMentoriaRepository sesionMentoriaRepository;

    //Obtener todas las sesiones de mentoria
    @GetMapping
    public ResponseEntity<List<SesionMentoria>> getAllSesionMentorias() {
        List<SesionMentoria> sesionMentorias = sesionMentoriaService.getAll();
        return new ResponseEntity<List<SesionMentoria>>(sesionMentorias,HttpStatus.OK);
    }

    //Crea una nueva sesion de mentoria
    @PutMapping
    public ResponseEntity<SesionMentoria> createSesionMentoria(@RequestBody SesionMentoria sesionMentoria) {

        SesionMentoria sesionMentoria1 = sesionMentoriaService.create(sesionMentoria);
        return new ResponseEntity<SesionMentoria>(sesionMentoria1, HttpStatus.CREATED);
    }

    //Actualiza una sesion de mentoria existente
    @PutMapping("/{id}")
    public ResponseEntity<SesionMentoria> updateSesionMentoria(@PathVariable("id") Integer id, @RequestBody SesionMentoria sesionMentoria) {
        SesionMentoria updatedMentor = sesionMentoriaService.update(id, sesionMentoria);
        return new ResponseEntity<SesionMentoria>(updatedMentor, HttpStatus.OK);
    }

    //Elimina la sesion de mentoria por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesionMentoria(@PathVariable("id") Integer id) {
        sesionMentoriaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Busca una sesion de mentoria por id
    @GetMapping("/{id}")
    public ResponseEntity<SesionMentoria> getSesionMentoriaById(@PathVariable("id") Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaService.searchById(id);
        return new ResponseEntity<SesionMentoria>(sesionMentoria, HttpStatus.OK);
    }

    //Obtener las sesiones de mentoria por page
    @GetMapping("/page")
    public ResponseEntity<Page<SesionMentoria>> paginateSesionesMentoria(@PageableDefault(size = 5, sort = "name")Pageable pageable) {
        Page<SesionMentoria> sesionesMentoria = sesionMentoriaService.paginate(pageable);
        return new ResponseEntity<Page<SesionMentoria>>(sesionesMentoria, HttpStatus.OK);
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearSesionMentoria(
            @RequestBody CrearSesionMentoriaRequestDTO requestDTO,
            @RequestHeader("Authorization") String bearerToken) {

            // Obtener el ID del estudiante desde el token
            Usuario usuario = usuarioService.getUsuario(bearerToken);
            Estudiante estudiante = estudianteRepository.findByUsuarioid(usuario);

            // Completar el DTO con el ID del estudiante
            requestDTO.setIdEstudiante(estudiante.getId());

            // Delegar la creación al servicio
            SesionMentoria nuevaSesion = sesionMentoriaService.crearSesionMentoria(requestDTO);

            return ResponseEntity.ok(nuevaSesion);
    }

}

package com.reservamentor.api;

import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.dto.InformacionMentorDTO1;
import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.dto.MentorUpdateRequestDTO;
import com.reservamentor.model.entity.Asignatura;
import com.reservamentor.service.AsignaturaService;

import com.reservamentor.service.MentorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    private final MentorService mentorService;
    private final AsignaturaService asignaturaService;

    // Constructor con inyección de dependencia
    public MentorController(MentorService mentorService, AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
        this.mentorService = mentorService;
    }

    //Busqueda de todos los mentores

    @GetMapping
    public ResponseEntity<List<InformacionMentorDTO>> getAllMentors() {
        List<InformacionMentorDTO> mentors = mentorService.getAll();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    //Busqueda de todos los mentores y ordenar por valoracion
    @GetMapping("/sort/rating")
    public ResponseEntity<List<InformacionMentorDTO>> sortAllByRating() {
        List<InformacionMentorDTO> mentors = mentorService.sortAllByRating();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    //Busqueda de mentores por ID

    @GetMapping("/profile/{id}")
    public ResponseEntity<MentorPerfilDTO> findMentor(@PathVariable Integer id) {
        MentorPerfilDTO mentor = mentorService.searchById(id);
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }

    //Busqueda de mentores por dia
    @GetMapping("/search/dia")
    public ResponseEntity<List<InformacionMentorDTO>> searchMentorsByDia(@RequestParam("dia") String dia) {
        List<InformacionMentorDTO> mentors = mentorService.searchByDia(dia);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    //Busqueda de mentores por hora
    @GetMapping("/search/hora")
    public ResponseEntity<List<InformacionMentorDTO>> searchMentorsByHora(@RequestParam("horaInicio") String horaInicioStr, @RequestParam("horaFin") String horaFinStr) {
        LocalTime horaInicio = LocalTime.parse(horaInicioStr);
        LocalTime horaFinal = LocalTime.parse(horaFinStr);

        List<InformacionMentorDTO> mentors = mentorService.searchByHora(horaInicio, horaFinal);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    //Busqueda de mentores por dia y hora
    @GetMapping("/search/hora-y-dia")
    public ResponseEntity<List<InformacionMentorDTO>> searchMentorsByDiaAndHora(@RequestParam("dia") String dia, @RequestParam("horaInicio") String horaInicioStr, @RequestParam("horaFin") String horaFinStr) {
        LocalTime horaInicio = LocalTime.parse(horaInicioStr);
        LocalTime horaFinal = LocalTime.parse(horaFinStr);

        List<InformacionMentorDTO> mentors = mentorService.searchByDiaAndHora(dia, horaInicio, horaFinal);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    //Busqueda de todas area de conocimiento
    @GetMapping("/search/asignatura")
    public ResponseEntity<List<Asignatura>> getAsignaturas() {
        List<Asignatura> asignaturas = asignaturaService.getAllAsignaturas();
        return new ResponseEntity<>(asignaturas, HttpStatus.OK);
    }

    //Busqueda de mentores por area de conocimiento
    @GetMapping("/search/asignatura/{id}")
    public ResponseEntity<List<InformacionMentorDTO1>> searchMentoresByAsignatura(@PathVariable Integer id) {
        List<InformacionMentorDTO1> mentors = asignaturaService.getMentoresByAsignaturaId(id);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    //Creacion de mentores
    @PostMapping
    public ResponseEntity<InformacionMentorDTO> createMentor(@Valid @RequestBody InformacionMentorDTO mentorDTO) {
        InformacionMentorDTO newMentor = mentorService.create(mentorDTO);
        return new ResponseEntity<>(newMentor, HttpStatus.CREATED);
    }

    //Actualizacion de mentores
    //Solo actualiza la bibliografia o la tarifaHora

    @PutMapping("/{id}")
    public ResponseEntity<InformacionMentorDTO> updateMentor(@PathVariable Integer id, @Valid @RequestBody MentorUpdateRequestDTO mentorUpdateRequestDTO) {
        InformacionMentorDTO informacionMentorDTo = mentorService.update(mentorUpdateRequestDTO, id);
        return new ResponseEntity<>(informacionMentorDTo, HttpStatus.OK);
    }

    //Eliminacion de mentores

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Integer id) {
        mentorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Obtener mentores por pagina
    @GetMapping("/page")
    public ResponseEntity<Page<InformacionMentorDTO>> paginateMentors(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<InformacionMentorDTO> mentors = mentorService.getMentorsByPage(pageable);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

}

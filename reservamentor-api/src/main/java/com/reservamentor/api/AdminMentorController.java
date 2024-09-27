package com.reservamentor.api;

import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.AdminMentorService;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/mentor")
public class AdminMentorController {
    private final AdminMentorService adminMentorService;

    @GetMapping
    public ResponseEntity<List<InformacionMentorDTO>> getAllMentors() {
        List<InformacionMentorDTO> mentors = adminMentorService.getAll();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<InformacionMentorDTO>> paginateMentors(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<InformacionMentorDTO> mentors = adminMentorService.paginate(pageable);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacionMentorDTO> getMentorById(@PathVariable("id") Integer id) {
        InformacionMentorDTO mentor = adminMentorService.findById(id);
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }


    @GetMapping("/sorted-by-rating")
    public ResponseEntity<List<InformacionMentorDTO>> getMentorsSortedByRating() {
        List<InformacionMentorDTO> mentors = adminMentorService.getAllSortedByRating();
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @GetMapping("/by-dia")
    public ResponseEntity<List<InformacionMentorDTO>> getMentorsByDia(@RequestParam("dia") String dia) {
        List<InformacionMentorDTO> mentors = adminMentorService.getMentorsByDia(dia);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @GetMapping("/by-hora")
    public ResponseEntity<List<InformacionMentorDTO>> getMentorsByHora(@RequestParam("horaInicio") String horaInicioStr, @RequestParam("horaFin") String horaFinStr) {
        LocalTime horaInicio = LocalTime.parse(horaInicioStr);
        LocalTime horaFin = LocalTime.parse(horaFinStr);
        List<InformacionMentorDTO> mentors = adminMentorService.getMentorsByHora(horaInicio, horaFin);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @GetMapping("/by-dia-y-hora")
    public ResponseEntity<List<InformacionMentorDTO>> getMentorsByDiaAndHora(@RequestParam("dia") String dia, @RequestParam("horaInicio") String horaInicioStr, @RequestParam("horaFin") String horaFinStr) {
        LocalTime horaInicio = LocalTime.parse(horaInicioStr);
        LocalTime horaFin = LocalTime.parse(horaFinStr);
        List<InformacionMentorDTO> mentors = adminMentorService.getMentorsByDiaAndHora(dia, horaInicio, horaFin);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InformacionMentorDTO> createMentor(@Valid @RequestBody InformacionMentorDTO informacionMentorDTO) {
        InformacionMentorDTO newMentor = adminMentorService.create(informacionMentorDTO);
        return new ResponseEntity<>(newMentor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformacionMentorDTO> updateMentor(@PathVariable("id") Integer id, @Valid @RequestBody InformacionMentorDTO informacionMentorDTO) {
        InformacionMentorDTO updateMentor = adminMentorService.update(id, informacionMentorDTO);
        return new ResponseEntity<>(updateMentor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable("id") Integer id) {
        adminMentorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


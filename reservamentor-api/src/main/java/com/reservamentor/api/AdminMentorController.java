package com.reservamentor.api;

import com.reservamentor.model.entity.Mentor;
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

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/mentor")
public class AdminMentorController {
    private final AdminMentorService adminMentorService;

    @GetMapping
    public ResponseEntity<List<Mentor>> getAllMentors() {
        List<Mentor> mentors = adminMentorService.getAll();
        return new ResponseEntity<List<Mentor>>(mentors, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Mentor>> paginateMentors(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<Mentor> mentors = adminMentorService.paginate(pageable);
        return new ResponseEntity<Page<Mentor>>(mentors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable("id") Integer id) {
        Mentor mentor = adminMentorService.findById(id);
        return new ResponseEntity<Mentor>(mentor, HttpStatus.OK);
    }


    @GetMapping("/sorted-by-rating")
    public ResponseEntity<List<Mentor>> getMentorsSortedByRating() {
        List<Mentor> mentors = adminMentorService.getAllSortedByRating();
        return new ResponseEntity<List<Mentor>>(mentors, HttpStatus.OK);
    }

    @GetMapping("/by-dia")
    public ResponseEntity<List<Mentor>> getMentorsByDia(@RequestParam("dia") String dia) {
        List<Mentor> mentors = adminMentorService.getMentorsByDia(dia);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @GetMapping("/by-hora")
    public ResponseEntity<List<Mentor>> getMentorsByHora(@RequestParam("horaInicio") String horaInicioStr, @RequestParam("horaFin") String horaFinStr) {
        LocalTime horaInicio = LocalTime.parse(horaInicioStr);
        LocalTime horaFin = LocalTime.parse(horaFinStr);
        List<Mentor> mentors = adminMentorService.getMentorsByHora(horaInicio, horaFin);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @GetMapping("/by-dia-y-hora")
    public ResponseEntity<List<Mentor>> getMentorsByDiaAndHora(@RequestParam("dia") String dia, @RequestParam("horaInicio") String horaInicioStr, @RequestParam("horaFin") String horaFinStr) {
        LocalTime horaInicio = LocalTime.parse(horaInicioStr);
        LocalTime horaFin = LocalTime.parse(horaFinStr);
        List<Mentor> mentors = adminMentorService.getMentorsByDiaAndHora(dia, horaInicio, horaFin);
        return new ResponseEntity<>(mentors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor) {
        Mentor newMentor = adminMentorService.create(mentor);
        return new ResponseEntity<Mentor>(newMentor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable("id") Integer id, @RequestBody Mentor mentor) {
        Mentor updateMentor = adminMentorService.update(id, mentor);
        return new ResponseEntity<Mentor>(updateMentor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mentor> deleteMentor(@PathVariable("id") Integer id) {
        adminMentorService.delete(id);
        return new ResponseEntity<Mentor>(HttpStatus.NO_CONTENT);
    }
}

package com.reservamentor.api;

import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.service.AdminMentoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reservamentor.service.AdminSesionMentoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/sesionmentoria")
public class AdminSesionMentoriaController {
    private final AdminMentoriaService adminMentoriaService;
    private final AdminSesionMentoriaService adminSesionMentoriaService;


    @PostMapping
    public ResponseEntity<SesionMentoria> createSesionMentoria(@RequestBody SesionMentoria inputSesionMentoria){
        SesionMentoria newSesionMentoria = adminMentoriaService.create(inputSesionMentoria);
        return new ResponseEntity<SesionMentoria>(newSesionMentoria, HttpStatus.CREATED);
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Sesionmentoria> deleteSesionMentoria(@PathVariable("id") Integer id){
        adminMentoriaService.delete(id);
        return new ResponseEntity<Sesionmentoria>(HttpStatus.NO_CONTENT);
    }
    
 */

    @GetMapping("/{id}")
    public ResponseEntity<SesionMentoria> getSesionMentoriaByID(@PathVariable("id") Integer id){
        SesionMentoria sesionMentoria = adminMentoriaService.findById(id);
        return new ResponseEntity<>(sesionMentoria, HttpStatus.OK);
    }


    /*

    @PostMapping("/{id}")
    public ResponseEntity<Sesionmentoria> updateSesionMentoria(@PathVariable("id") Integer id, @RequestBody Sesionmentoria inputSesionmentoria){
        Sesionmentoria newSesionMentoria = adminMentoriaService.update(id, inputSesionmentoria);
        return new ResponseEntity<Sesionmentoria>(newSesionMentoria, HttpStatus.OK);
    }

    @PostMapping("/reprogramarMentoria/{id}")
    public ResponseEntity<Sesionmentoria> reprogramarSesionMentoria(@PathVariable("id") Integer id, @RequestBody Sesionmentoria inputSesionmentoria){
        Sesionmentoria newSesionMentoria = adminMentoriaService.reprogramarMentoria(id, inputSesionmentoria);
        return new ResponseEntity<Sesionmentoria>(newSesionMentoria, HttpStatus.OK);
    }
        */

    @GetMapping
    public ResponseEntity<List<SesionMentoria>> getAllSesions() {
        List<SesionMentoria> mentors = adminSesionMentoriaService.getAll();
        return new ResponseEntity<List<SesionMentoria>>(mentors, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SesionMentoria>> paginateSesions(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<SesionMentoria> mentors = adminSesionMentoriaService.paginate(pageable);
        return new ResponseEntity<Page<SesionMentoria>>(mentors, HttpStatus.OK);
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<Sesionmentoria> getSesionById(@PathVariable("id") Integer id) {
        Sesionmentoria mentor = adminSesionMentoriaService.findById(id);
        return new ResponseEntity<Sesionmentoria>(mentor, HttpStatus.OK);
    }
 */
    
    /*
    @PostMapping
    public ResponseEntity<Sesionmentoria> createSesion(@RequestBody Sesionmentoria mentor) {
        Sesionmentoria newMentor = adminSesionMentoriaService.create(mentor);
        return new ResponseEntity<Sesionmentoria>(newMentor, HttpStatus.CREATED);
    }
    
     */

    @PutMapping("/{id}")
    public ResponseEntity<SesionMentoria> updateSesion(@PathVariable("id") Integer id, @RequestBody SesionMentoria mentor) {
        SesionMentoria updateMentor = adminSesionMentoriaService.update(id, mentor);
        return new ResponseEntity<SesionMentoria>(updateMentor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SesionMentoria> deleteSesion(@PathVariable("id") Integer id) {
        adminSesionMentoriaService.delete(id);
        return new ResponseEntity<SesionMentoria>(HttpStatus.NO_CONTENT);
    }
}

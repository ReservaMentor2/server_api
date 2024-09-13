package com.reservamentor.api;

import com.reservamentor.model.entity.Sesionmentoria;
import com.reservamentor.service.AdminMentoriaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/sesionmentoria")
public class AdminSesionMentoriaController {
    private final AdminMentoriaService adminMentoriaService;

    @PostMapping
    public ResponseEntity<Sesionmentoria> createSesionMentoria(@RequestBody Sesionmentoria inputSesionMentoria){
        Sesionmentoria newSesionMentoria = adminMentoriaService.create(inputSesionMentoria);
        return new ResponseEntity<Sesionmentoria>(newSesionMentoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sesionmentoria> deleteSesionMentoria(@PathVariable("id") Integer id){
        adminMentoriaService.delete(id);
        return new ResponseEntity<Sesionmentoria>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sesionmentoria> getSesionMentoriaByID(@PathVariable("id") Integer id){
        Sesionmentoria sesionMentoria = adminMentoriaService.findById(id);
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



}

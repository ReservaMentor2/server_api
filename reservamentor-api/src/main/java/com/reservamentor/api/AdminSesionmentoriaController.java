package com.reservamentor.api;

import com.reservamentor.dto.SesionmentoriaDTO;
import com.reservamentor.service.AdminSesionmentoriaService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/sesionmentoria")
public class AdminSesionmentoriaController {
    private final AdminSesionmentoriaService adminSesionmentoriaService;

    @GetMapping
    public ResponseEntity<List<SesionmentoriaDTO>> getAllSesions() {
        List<SesionmentoriaDTO> sesiones = adminSesionmentoriaService.getAll();
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SesionmentoriaDTO>> paginateSesions(@PageableDefault(size = 5, sort = "dia") Pageable pageable) {
        Page<SesionmentoriaDTO> sesiones = adminSesionmentoriaService.paginate(pageable);
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionmentoriaDTO> getSesionById(@PathVariable("id") Integer id) {
        SesionmentoriaDTO sesion = adminSesionmentoriaService.findById(id);
        return new ResponseEntity<>(sesion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SesionmentoriaDTO> createSesion(@RequestBody SesionmentoriaDTO sesionmentoriaDTO) {
        SesionmentoriaDTO newSesion = adminSesionmentoriaService.create(sesionmentoriaDTO);
        return new ResponseEntity<>(newSesion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionmentoriaDTO> updateSesion(@PathVariable("id") Integer id, @RequestBody SesionmentoriaDTO sesionmentoriaDTO) {
        SesionmentoriaDTO updatedSesion = adminSesionmentoriaService.update(id, sesionmentoriaDTO);
        return new ResponseEntity<>(updatedSesion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesion(@PathVariable("id") Integer id) {
        adminSesionmentoriaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
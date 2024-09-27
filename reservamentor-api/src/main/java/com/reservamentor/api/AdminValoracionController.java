package com.reservamentor.api;

import com.reservamentor.dto.ValoracionDTO;
import com.reservamentor.service.AdminValoracionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/valoraciones")
public class AdminValoracionController {

    @Autowired
    private AdminValoracionService adminValoracionService;

    @PostMapping("/crear")
    public ResponseEntity<ValoracionDTO> crearValoracion(@Validated @RequestBody ValoracionDTO valoracionDTO) {
        ValoracionDTO nuevaValoracion = adminValoracionService.crearValoracion(valoracionDTO);
        return new ResponseEntity<>(nuevaValoracion, HttpStatus.CREATED);
    }

}

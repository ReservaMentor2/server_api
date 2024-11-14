package com.reservamentor.api;

import com.reservamentor.dto.CursoCortoDTO;
import com.reservamentor.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@PreAuthorize("hasAnyRole('MENTOR', 'ADMIN')")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/mentor/{mentorId}")
    public List<CursoCortoDTO> obtenerCursosCortosPorMentor(@PathVariable Integer mentorId) {
        return cursoService.obtenerCursosCortosPorMentor(mentorId);
    }
}

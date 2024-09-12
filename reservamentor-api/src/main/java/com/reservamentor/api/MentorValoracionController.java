package com.reservamentor.api;

import com.reservamentor.model.entity.Valoracion;
import com.reservamentor.service.MentorValoracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class MentorValoracionController {

    private final MentorValoracionService mentorValoracionService;

    //Retorna informacion basica como cantidad de reviews y valoracion
    //@GetMapping("/{mentorId}/")

    //Retorna los comentarios del mentor
    @GetMapping("/comments/{mentorId}")
    public ResponseEntity<List<Valoracion>> findValoracionesByMentor(@PathVariable Integer mentorId) {
        List<Valoracion> valoraciones = mentorValoracionService.findValoracionesByMentor(mentorId);
        return ResponseEntity.ok(valoraciones);
    }
}

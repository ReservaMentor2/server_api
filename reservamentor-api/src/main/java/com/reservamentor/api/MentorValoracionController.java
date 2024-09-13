package com.reservamentor.api;

import com.reservamentor.model.entity.Valoracion;
import com.reservamentor.service.MentorValoracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class MentorValoracionController {

    private final MentorValoracionService mentorValoracionService;

    // Retorna los comentarios del mentor
    @GetMapping("/comments/{mentorId}")
    public ResponseEntity<List<Valoracion>> findValoracionesByMentor(@PathVariable Integer mentorId) {
        List<Valoracion> valoraciones = mentorValoracionService.findValoracionesByMentor(mentorId);
        return ResponseEntity.ok(valoraciones);
    }

    // Filtra valoraciones por estrellas
    @GetMapping("/filter/{estrellas}")
    public ResponseEntity<List<Valoracion>> filterValoracionesByEstrellas(@PathVariable Integer estrellas) {
        List<Valoracion> valoraciones = mentorValoracionService.filterValoracionesByEstrellas(estrellas);
        return ResponseEntity.ok(valoraciones);
    }
}

package com.reservamentor.api;

import com.reservamentor.model.entity.Valoracion;
import com.reservamentor.service.MentorValoracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ESTUDIANTE', 'ADMIN')")
public class ValoracionController {

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

    //Calificación promedio del profesor en escala de 5 estrellas
    @GetMapping("/average/{mentorId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Integer mentorId) {
        Double averageRating = mentorValoracionService.calculateAverageRating(mentorId);
        return ResponseEntity.ok(averageRating);
    }
}

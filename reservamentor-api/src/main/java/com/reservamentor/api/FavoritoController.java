package com.reservamentor.api;

import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.model.entity.Favorito;
import com.reservamentor.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@PreAuthorize("hasAnyRole('ESTUDIANTE', 'ADMIN')")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("/{estudianteID}")
    public List<MentorPerfilDTO> listarFavoritos(@PathVariable Integer estudianteID) {
        return favoritoService.listarFavoritos(estudianteID);
    }

    @PutMapping("/add/{estudianteID}/{mentorID}")
    public ResponseEntity<Favorito> agregarFavorito(@PathVariable Integer estudianteID, @PathVariable Integer mentorID) {
        Favorito newFavorito = favoritoService.createFavorito(mentorID, estudianteID);
        return new ResponseEntity<>(newFavorito, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{estudianteID}/{mentorID}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Integer estudianteID, @PathVariable Integer mentorID)  {
        favoritoService.deleteFavorito(estudianteID, mentorID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

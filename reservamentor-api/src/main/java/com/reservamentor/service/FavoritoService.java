package com.reservamentor.service;

import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.model.entity.Favorito;

import java.util.List;

public interface FavoritoService {

    Favorito createFavorito(Integer mentorId, Integer estudianteId);
    List<MentorPerfilDTO> listarFavoritos(int estudianteId);
    void deleteFavorito(Integer mentorId, Integer estudianteId);
}
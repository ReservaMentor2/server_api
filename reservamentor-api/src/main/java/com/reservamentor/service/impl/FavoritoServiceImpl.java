package com.reservamentor.service.impl;

import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.model.entity.Favorito;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.EstudianteRepository;
import com.reservamentor.repository.FavoritoRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.FavoritoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final MentorRepository mentorRepository;
    private final EstudianteRepository estudianteRepository;

    public FavoritoServiceImpl(FavoritoRepository favoritoRepository, MentorRepository mentorRepository, EstudianteRepository estudianteRepository) {
        this.favoritoRepository = favoritoRepository;
        this.mentorRepository = mentorRepository;
        this.estudianteRepository = estudianteRepository;
    }

    private MentorPerfilDTO convertirAMentorPublicoDTO(Mentor mentor) {
        return new MentorPerfilDTO(
                mentor.getUsuarioId().getNombre(),
                mentor.getUsuarioId().getApellido(),
                mentor.getUsuarioId().getCorreo(),
                mentor.getUsuarioId().getNacionalidad(),
                mentor.getUsuarioId().getTelefono(),
                mentor.getValoracionpromedio(),
                mentor.getTarifahora(),
                mentor.getBiografia(),
                ""
        );
    }

    @Override
    public List<MentorPerfilDTO> listarFavoritos(int estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not found"));

        List<Favorito> favoritos = favoritoRepository.findByEstudianteid(estudiante);
        return favoritos.stream()
                .map(favorito -> convertirAMentorPublicoDTO(favorito.getMentorid()))
                .collect(Collectors.toList());
    }

    @Override
    public Favorito createFavorito(Integer mentorId, Integer estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                 .orElseThrow(() -> new ResourceNotFoundException("Estudiante not found"));

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not found"));

        boolean isFavorito = favoritoRepository.existsByEstudianteidAndMentorid(estudiante, mentor);

        if (isFavorito) {
            throw new ResourceNotFoundException("Ya es favorito el mentor y el estudiante");
        }

        Favorito favorito = new Favorito();

        favorito.setEstudianteid(estudiante);
        favorito.setMentorid(mentor);

        return favoritoRepository.save(favorito);

    }

    @Override
    public void deleteFavorito(Integer mentorId, Integer estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not found"));

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante not found"));

        Favorito favorito = favoritoRepository.findByEstudianteidAndMentorid(estudiante, mentor);

        favoritoRepository.delete(favorito);
    }

}

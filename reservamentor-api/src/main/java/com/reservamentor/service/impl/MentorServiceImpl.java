package com.reservamentor.service.impl;

import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;

    @Override
    public Mentor createMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Override
    public List<Mentor> getAllMentores() {
        return mentorRepository.findAll();
    }

    @Override
    public MentorPerfilDTO getMentorPerfil(Integer mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        Usuario usuario = mentor.getUsuario();

        return new MentorPerfilDTO(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getNacionalidad(),
                usuario.getTelefono(),
                mentor.getValoracionpromedio(),
                mentor.getTarifahora(),
                mentor.getBiografia()
        );
    }
    @Override
    public Optional<Mentor> getMentorById(Integer id) {
        return mentorRepository.findById(id);
    }
}

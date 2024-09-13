package com.reservamentor.service;

import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.MentorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;

    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    // Obtener todos los mentores
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    // Obtener un mentor por ID
    public Mentor getMentorById(Integer id) {
        Optional<Mentor> mentor = mentorRepository.findById(id);
        return mentor.orElse(null);
    }

    // Crear un nuevo mentor
    public Mentor createMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    // Actualizar un mentor
    public Mentor updateMentor(Integer id, Mentor mentorDetails) {
        return mentorRepository.findById(id).map(mentor -> {
            mentor.setBiografia(mentorDetails.getBiografia());
            mentor.setTarifahora(mentorDetails.getTarifahora());
            mentor.setValoracionpromedio(mentorDetails.getValoracionpromedio());
            return mentorRepository.save(mentor);
        }).orElse(null);
    }

    // Eliminar un mentor
    public boolean deleteMentor(Integer id) {
        if (mentorRepository.existsById(id)) {
            mentorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

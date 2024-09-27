package com.reservamentor.service;

import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.model.entity.Mentor;

import java.util.List;
import java.util.Optional;

public interface MentorService {

    Mentor createMentor(Mentor mentor);
    List<Mentor> getAllMentores();
    MentorPerfilDTO getMentorPerfil(Integer mentorId);
    Optional<Mentor> getMentorById(Integer mentorId);
}

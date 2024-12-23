package com.reservamentor.service.impl;

import com.reservamentor.dto.PreferenciasMentorDTO;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.RecomendacionMentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacionMentorServiceImpl implements RecomendacionMentorService {
    @Autowired
    private MentorRepository mentorRepository;

    public List<Mentor> recomendarMentores(PreferenciasMentorDTO preferencias) {
        return mentorRepository.findMentoresRecomendados(
                preferencias.getTarifaMaxima(),
                preferencias.getValoracionMinima(),
                preferencias.getHoraInicioPreferida(),
                preferencias.getHoraFinPreferida()
        );
    }
}

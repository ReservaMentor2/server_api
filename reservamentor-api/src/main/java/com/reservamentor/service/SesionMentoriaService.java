package com.reservamentor.service;

import com.reservamentor.model.entity.Sesionmentoria;
import com.reservamentor.repository.SesionMentoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionMentoriaService {

    private final SesionMentoriaRepository sesionMentoriaRepository;

    public SesionMentoriaService(SesionMentoriaRepository sesionMentoriaRepository) {
        this.sesionMentoriaRepository = sesionMentoriaRepository;
    }

    public List<Sesionmentoria> getAllSesionesMentoria() {
        return sesionMentoriaRepository.findAll();
    }

    public Sesionmentoria getSesionMentoriaById(Integer id) {
        return sesionMentoriaRepository.findById(id).orElse(null);
    }

    public Sesionmentoria createSesionMentoria(Sesionmentoria sesionMentoria) {
        return sesionMentoriaRepository.save(sesionMentoria);
    }

    public Sesionmentoria updateSesionMentoria(Integer id, Sesionmentoria sesionMentoria) {
        if (sesionMentoriaRepository.existsById(id)) {
            sesionMentoria.setId(id);
            return sesionMentoriaRepository.save(sesionMentoria);
        }
        return null;
    }

    public boolean deleteSesionMentoria(Integer id) {
        if (sesionMentoriaRepository.existsById(id)) {
            sesionMentoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.reservamentor.service.impl;

import com.reservamentor.model.entity.Sesionmentoria;
import com.reservamentor.repository.MentoriaRepository;
import com.reservamentor.service.AdminMentoriaService;
import org.springframework.transaction.annotation.Transactional;import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSesionMentoriaImpl implements AdminMentoriaService {

    private final MentoriaRepository mentoriaRepository;
    public AdminSesionMentoriaImpl(MentoriaRepository mentoriaRepository){
        this.mentoriaRepository = mentoriaRepository;
    }

    @Transactional
    @Override
    public Sesionmentoria create(Sesionmentoria sesionmentoria) {
        return mentoriaRepository.save(sesionmentoria);
    }

    @Transactional()
    @Override
    public Sesionmentoria update(Integer id, Sesionmentoria updateSesionMentoria) {
        Sesionmentoria mentoriaFromDB = findById(id);
        mentoriaFromDB.setPrecio(updateSesionMentoria.getPrecio());
        mentoriaFromDB.setDia(updateSesionMentoria.getDia());
        mentoriaFromDB.setWeblink(updateSesionMentoria.getWeblink());
        mentoriaFromDB.setHorainicio(updateSesionMentoria.getHorainicio());
        mentoriaFromDB.setHorafinal(updateSesionMentoria.getHorafinal());
        mentoriaFromDB.setMentorid(updateSesionMentoria.getMentorid());
        mentoriaFromDB.setEstudianteid(updateSesionMentoria.getEstudianteid());
        mentoriaFromDB.setAsignaturaid(updateSesionMentoria.getAsignaturaid());
        mentoriaFromDB.setTurnoid(updateSesionMentoria.getTurnoid());
        return mentoriaRepository.save(mentoriaFromDB);
    }

    @Transactional
    @Override
    public Sesionmentoria delete(Integer id, Sesionmentoria sesionmentoria) {
        Sesionmentoria mentoriaFromDB = findById(id);
        mentoriaRepository.delete(mentoriaFromDB);
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Sesionmentoria findById(Integer id){
        return mentoriaRepository.findById(id).orElseThrow( () -> new RuntimeException("Sesion de mentoria not found"));
    }

    @Transactional
    @Override
    public Sesionmentoria reprogramarMentoria(Integer id, Sesionmentoria updateSesionMentoria) {
        Sesionmentoria mentoriaFromDB = findById(id);
        mentoriaFromDB.setHorainicio(updateSesionMentoria.getHorainicio());
        mentoriaFromDB.setHorainicio(updateSesionMentoria.getHorafinal());
        return mentoriaRepository.save(mentoriaFromDB);
    }
}

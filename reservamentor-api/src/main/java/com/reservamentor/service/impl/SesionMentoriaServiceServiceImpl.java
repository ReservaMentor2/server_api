package com.reservamentor.service.impl;

import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.repository.*;
import com.reservamentor.service.SesionMentoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SesionMentoriaServiceServiceImpl implements SesionMentoriaService {

    private final SesionMentoriaRepository sesionMentoriaRepository;
    private final EstudianteRepository estudianteRepository;
    private final MentorRepository mentorRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final TurnoRepository turnoRepository;


    public SesionMentoriaServiceServiceImpl(SesionMentoriaRepository sesionMentoriaRepository1, EstudianteRepository estudianteRepository1, MentorRepository mentorRepository1, AsignaturaRepository asignaturaRepository1, TurnoRepository turnoRepository1) {
        this.sesionMentoriaRepository = sesionMentoriaRepository1;
        this.estudianteRepository = estudianteRepository1;
        this.mentorRepository = mentorRepository1;
        this.asignaturaRepository = asignaturaRepository1;
        this.turnoRepository = turnoRepository1;
    }

    @Transactional
    @Override
    public List<SesionMentoria> getAll() {
        return sesionMentoriaRepository.findAll();
    }

    @Transactional
    @Override
    public SesionMentoria searchById(Integer id) {
        return sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesión de mentoría con el ID " + id + " no fue encontrada")
        );
    }

    @Transactional
    @Override
    public Page<SesionMentoria> paginate(Pageable pageable) {
        return sesionMentoriaRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public SesionMentoria create(SesionMentoria sesionMentoria) {
        return sesionMentoriaRepository.save(sesionMentoria);
    }

    @Transactional
    @Override
    public SesionMentoria update(Integer id, SesionMentoria sesionMentoriaUpdated) {
        SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesión de mentoría con el ID " + id + " no fue encontrada")
        );

        sesionMentoria.setPrecio(sesionMentoriaUpdated.getPrecio());
        sesionMentoria.setDia(sesionMentoriaUpdated.getDia());
        sesionMentoria.setWeblink(sesionMentoriaUpdated.getWeblink());
        sesionMentoria.setHorainicio(sesionMentoriaUpdated.getHorainicio());
        sesionMentoria.setHorafinal(sesionMentoriaUpdated.getHorafinal());
        sesionMentoria.setMentorid(sesionMentoriaUpdated.getMentorid());
        sesionMentoria.setEstudianteid(sesionMentoriaUpdated.getEstudianteid());
        sesionMentoria.setTurnoid(sesionMentoriaUpdated.getTurnoid());
        sesionMentoria.setAsignaturaid(sesionMentoriaUpdated.getAsignaturaid());

        return sesionMentoriaRepository.save(sesionMentoria);
    }

    @Transactional
    public void delete(Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesión de mentoría con el ID " + id + " no fue encontrada")
        );

        sesionMentoriaRepository.delete(sesionMentoria);
    }
}

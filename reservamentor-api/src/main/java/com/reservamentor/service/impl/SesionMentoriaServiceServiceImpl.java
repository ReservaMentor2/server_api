package com.reservamentor.service.impl;

import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.*;
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

    public SesionMentoriaServiceServiceImpl(SesionMentoriaRepository sesionMentoriaRepository,
                                            EstudianteRepository estudianteRepository,
                                            MentorRepository mentorRepository,
                                            AsignaturaRepository asignaturaRepository,
                                            TurnoRepository turnoRepository) {
        this.sesionMentoriaRepository = sesionMentoriaRepository;
        this.estudianteRepository = estudianteRepository;
        this.mentorRepository = mentorRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.turnoRepository = turnoRepository;
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

        // Validación y seteo de Mentor
        Mentor mentor = mentorRepository.findById(sesionMentoriaUpdated.getMentorid().getId())
                .orElseThrow(() -> new ResourceNotFoundException("El mentor con el ID " + sesionMentoriaUpdated.getMentorid().getId() + " no fue encontrado"));
        sesionMentoria.setMentorid(mentor);

        // Validación y seteo de Estudiante
        Estudiante estudiante = estudianteRepository.findById(sesionMentoriaUpdated.getEstudianteid().getId())
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante con el ID " + sesionMentoriaUpdated.getEstudianteid().getId() + " no fue encontrado"));
        sesionMentoria.setEstudianteid(estudiante);

        // Validación y seteo de Asignatura
        Asignatura asignatura = asignaturaRepository.findById(sesionMentoriaUpdated.getAsignaturaid().getId())
                .orElseThrow(() -> new ResourceNotFoundException("La asignatura con el ID " + sesionMentoriaUpdated.getAsignaturaid().getId() + " no fue encontrada"));
        sesionMentoria.setAsignaturaid(asignatura);

        // Validación y seteo de Turno
        Turno turno = turnoRepository.findById(sesionMentoriaUpdated.getTurnoid().getId())
                .orElseThrow(() -> new ResourceNotFoundException("El turno con el ID " + sesionMentoriaUpdated.getTurnoid().getId() + " no fue encontrado"));
        sesionMentoria.setTurnoid(turno);

        // Otros atributos
        sesionMentoria.setPrecio(sesionMentoriaUpdated.getPrecio());
        sesionMentoria.setDia(sesionMentoriaUpdated.getDia());
        sesionMentoria.setWeblink(sesionMentoriaUpdated.getWeblink());
        sesionMentoria.setHorainicio(sesionMentoriaUpdated.getHorainicio());
        sesionMentoria.setHorafinal(sesionMentoriaUpdated.getHorafinal());

        return sesionMentoriaRepository.save(sesionMentoria);
    }

    @Transactional
    public void delete(Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesión de mentoría con el ID " + id + " no fue encontrada")
        );

        sesionMentoriaRepository.delete(sesionMentoria);
    }

    @Transactional
    @Override
    public void setMentorias(Integer id, List<SesionMentoria> sesiones) {
        // Verifica si el mentor, estudiante o asignatura existe (según la lógica de tu negocio)
        Mentor mentor = mentorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Mentor con el ID " + id + " no fue encontrado"));

        // Itera sobre las sesiones de mentoría y asócialas al mentor
        for (SesionMentoria sesion : sesiones) {
            sesion.setMentorid(mentor);  // Asigna el mentor a cada sesión
            sesionMentoriaRepository.save(sesion);  // Guarda la sesión
        }
    }


}

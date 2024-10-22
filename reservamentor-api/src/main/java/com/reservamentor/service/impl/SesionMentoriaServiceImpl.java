package com.reservamentor.service.impl;

import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.repository.SesionMentoriaRepository;
import com.reservamentor.service.SesionMentoriaService;
import com.reservamentor.dto.SesionMentoriaFeedbackDTO;
import com.reservamentor.model.entity.Asignatura;
import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.AsignaturaRepository;
import com.reservamentor.repository.EstudianteRepository;
import com.reservamentor.repository.MentorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SesionMentoriaServiceImpl implements SesionMentoriaService {

    private final SesionMentoriaRepository sesionMentoriaRepository;
    private final MentorRepository mentorRepository;
    private final EstudianteRepository estudianteRepository;
    private final AsignaturaRepository asignaturaRepository;

    public SesionMentoriaServiceImpl(SesionMentoriaRepository sesionMentoriaRepository,
                                     MentorRepository mentorRepository,
                                     EstudianteRepository estudianteRepository,
                                     AsignaturaRepository asignaturaRepository) {
        this.sesionMentoriaRepository = sesionMentoriaRepository;
        this.mentorRepository = mentorRepository;
        this.estudianteRepository = estudianteRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    @Transactional
    @Override
    public List<SesionMentoria> getAll() {
        List<SesionMentoria> sesionMentorias = sesionMentoriaRepository.findAll();
        return sesionMentorias;
    }

    @Transactional
    @Override
    public SesionMentoria searchById(Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesion de mentoría con el ID " + id + " no fue encontrado"));
        return sesionMentoria;
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
                () -> new ResourceNotFoundException("La sesion de mentoría con el ID " + id + " no fue encontrado"));

        sesionMentoria.setPrecio(sesionMentoriaUpdated.getPrecio());
        sesionMentoria.setDia(sesionMentoriaUpdated.getDia());
        sesionMentoria.setHorainicio(sesionMentoriaUpdated.getHorainicio());
        sesionMentoria.setHorafinal(sesionMentoriaUpdated.getHorafinal());
        sesionMentoria.setWeblink(sesionMentoriaUpdated.getWeblink());
        sesionMentoria.setMentorid(sesionMentoriaUpdated.getMentorid());
        sesionMentoria.setEstudianteid(sesionMentoriaUpdated.getEstudianteid());
        sesionMentoria.setAsignaturaid(sesionMentoriaUpdated.getAsignaturaid());
        sesionMentoria.setTurnoid(sesionMentoriaUpdated.getTurnoid());

        return sesionMentoriaRepository.save(sesionMentoria);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesion de mentoría con el ID " + id + " no fue encontrado"));
        sesionMentoriaRepository.delete(sesionMentoria);
    }

    // Nuevo método para programar sesión de mentoría con retroalimentación
    @Transactional
    @Override
    public SesionMentoria programarSesionConFeedback(SesionMentoriaFeedbackDTO dto) {
        Mentor mentor = mentorRepository.findById(dto.getMentorId())
                .orElseThrow(() -> new RuntimeException("Mentor no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Asignatura asignatura = asignaturaRepository.findById(dto.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        SesionMentoria sesion = new SesionMentoria();
        sesion.setMentorid(mentor);
        sesion.setEstudianteid(estudiante);
        sesion.setAsignaturaid(asignatura);
        sesion.setDia(dto.getDia());
        sesion.setHorainicio(dto.getHoraInicio());
        sesion.setHorafinal(dto.getHoraFinal());
        sesion.setWeblink(dto.getWeblink());
        sesion.setPrecio(dto.getPrecio());
        sesion.setProgreso(dto.getProgreso()); // Nuevo campo para el progreso

        return sesionMentoriaRepository.save(sesion);
    }
}

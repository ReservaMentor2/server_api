package com.reservamentor.service.impl;

import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.repository.SesionMentoriaRepository;
import com.reservamentor.service.SesionMentoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SesionMentoriaServiceServiceImpl implements SesionMentoriaService {

    private final SesionMentoriaRepository sesionMentoriaRepository;

    public SesionMentoriaServiceServiceImpl(SesionMentoriaRepository sesionMentoriaRepository) {
        this.sesionMentoriaRepository = sesionMentoriaRepository;
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
        sesionMentoria.setMentorid(sesionMentoriaUpdated.getMentor());
        sesionMentoria.setEstudianteid(sesionMentoriaUpdated.getEstudiante());
        sesionMentoria.setAsignaturaid(sesionMentoriaUpdated.getAsignatura());
        sesionMentoria.setTurnoid(sesionMentoriaUpdated.getTurno());

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

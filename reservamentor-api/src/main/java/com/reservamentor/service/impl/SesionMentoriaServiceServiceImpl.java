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

    SesionMentoriaRepository sesionMentoriaRepository;

    @Transactional
    @Override
    public List<SesionMentoria> getAll(){
        List<SesionMentoria> sesionMentorias = sesionMentoriaRepository.findAll();
        return sesionMentorias;
    }

    @Transactional
    @Override
    public SesionMentoria searchById(Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesion de mentoria con el ID " + id + " no fue encontrado")
        );
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
                () -> new ResourceNotFoundException("La sesion de mentoria con el ID " + id + " no fue encontrado")
        );
        sesionMentoria.setPrecio(sesionMentoriaUpdated.getPrecio());
        sesionMentoria.setDia(sesionMentoriaUpdated.getDia());
        sesionMentoria.setWeblink(sesionMentoriaUpdated.getWeblink());
        sesionMentoria.setHorainicio(sesionMentoriaUpdated.getHorainicio());
        sesionMentoria.setHorafinal(sesionMentoriaUpdated.getHorafinal());
        sesionMentoria.setMentorid(sesionMentoriaUpdated.getMentorid());
        sesionMentoria.setEstudianteid(sesionMentoriaUpdated.getEstudianteid());
        sesionMentoria.setAsignaturaid(sesionMentoriaUpdated.getAsignaturaid());
        sesionMentoria.setTurnoid(sesionMentoriaUpdated.getTurnoid());

        return sesionMentoriaRepository.save(sesionMentoria);
    }

    @Transactional
    public void delete(Integer id) {
        SesionMentoria sesionMentoria = sesionMentoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La sesion de mentoria con el ID " + id + " no fue encontrado")
        );

        sesionMentoriaRepository.delete(sesionMentoria);
    }



}


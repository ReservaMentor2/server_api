package com.reservamentor.service.impl;

import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.repository.SesionMentoriaRepository;
import com.reservamentor.service.SesionMentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SesionMentoriaServiceImpl implements SesionMentoriaService {
    @Autowired
    private SesionMentoriaRepository sesionmentoriaRepository;


    @Transactional(readOnly = true)
    @Override
    public List<SesionMentoria> getAll() {
        return sesionmentoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SesionMentoria> paginate(Pageable pageable) {
        return sesionmentoriaRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public SesionMentoria create(SesionMentoria sesionmentoria) {
        return sesionmentoriaRepository.save(sesionmentoria);
    }

    @Transactional
    @Override
    public SesionMentoria update(Integer id, SesionMentoria updateSesionMentoria) {
        SesionMentoria sesionMentoriaFromDB = findById(id);
        sesionMentoriaFromDB.setDia(updateSesionMentoria.getDia());
        sesionMentoriaFromDB.setHorainicio(updateSesionMentoria.getHorainicio());
        sesionMentoriaFromDB.setHorafinal(updateSesionMentoria.getHorafinal());
        sesionMentoriaFromDB.setWeblink(updateSesionMentoria.getWeblink());
        return sesionmentoriaRepository.save(sesionMentoriaFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public SesionMentoria findById(Integer id) {
        return sesionmentoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sesion not found"));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        SesionMentoria sesionmentoria = findById(id);
        sesionmentoriaRepository.delete(sesionmentoria);

    }
}
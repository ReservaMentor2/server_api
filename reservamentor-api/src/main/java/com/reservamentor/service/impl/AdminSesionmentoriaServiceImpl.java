package com.reservamentor.service.impl;

import com.reservamentor.model.entity.Sesionmentoria;
import com.reservamentor.repository.SesionmentoriaRepository;
import com.reservamentor.service.AdminSesionmentoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AdminSesionmentoriaServiceImpl implements AdminSesionmentoriaService {
    @Autowired
    private SesionmentoriaRepository sesionmentoriaRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Sesionmentoria> getAll() {
        return sesionmentoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Sesionmentoria> paginate(Pageable pageable) {
        return sesionmentoriaRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Sesionmentoria create(Sesionmentoria sesionmentoria) {
        return sesionmentoriaRepository.save(sesionmentoria);
    }

    @Transactional
    @Override
    public Sesionmentoria update(Integer id, Sesionmentoria updateSesionmentoria) {
        Sesionmentoria sesionmentoriaFromDB = findById(id);
        sesionmentoriaFromDB.setDia(updateSesionmentoria.getDia());
        sesionmentoriaFromDB.setHorainicio(updateSesionmentoria.getHorainicio());
        sesionmentoriaFromDB.setHorafinal(updateSesionmentoria.getHorafinal());
        sesionmentoriaFromDB.setWeblink(updateSesionmentoria.getWeblink());
        return sesionmentoriaRepository.save(sesionmentoriaFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public Sesionmentoria findById(Integer id) {
        return sesionmentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesion not found"));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Sesionmentoria sesionmentoria = findById(id);
        sesionmentoriaRepository.delete(sesionmentoria);

    }
}
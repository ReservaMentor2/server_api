package com.reservamentor.service.impl;

import com.reservamentor.model.entity.Disponibilidad;
import com.reservamentor.repository.DisponibilidadRepository;
import com.reservamentor.service.AdminDisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminDisponibilidadServiceImpl implements AdminDisponibilidadService {
    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Disponibilidad> getAll() {
        return disponibilidadRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Disponibilidad> paginate(Pageable pageable) {
        return disponibilidadRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Disponibilidad create(Disponibilidad mentor) {
        return disponibilidadRepository.save(mentor);
    }

    @Transactional
    @Override
    public Disponibilidad update(Integer id, Disponibilidad updateDisponibilidad) {
        Disponibilidad disponibilidadFromDB = findById(id);
        disponibilidadFromDB.setDia(updateDisponibilidad.getDia());
        disponibilidadFromDB.setHorainicio(updateDisponibilidad.getHorainicio());
        disponibilidadFromDB.setHorafin(updateDisponibilidad.getHorafin());
        return disponibilidadRepository.save(disponibilidadFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public Disponibilidad findById(Integer id) {
        return disponibilidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Disponibilidad mentorFromDb = findById(id);
        disponibilidadRepository.delete(mentorFromDb);

    }

}
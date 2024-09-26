package com.reservamentor.service.impl;

import com.reservamentor.exception.BadRequestException;
import com.reservamentor.mapper.SesionmentoriaMapper;
import com.reservamentor.dto.SesionmentoriaDTO;
import com.reservamentor.model.entity.Sesionmentoria;
import com.reservamentor.repository.SesionmentoriaRepository;
import com.reservamentor.service.AdminSesionmentoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminSesionmentoriaServiceImpl implements AdminSesionmentoriaService {
    @Autowired
    private SesionmentoriaRepository sesionmentoriaRepository;

    @Autowired
    private SesionmentoriaMapper sesionmentoriaMapper;

    @Transactional(readOnly = true)
    @Override
    public List<SesionmentoriaDTO> getAll() {
        List<Sesionmentoria> sesiones = sesionmentoriaRepository.findAll();
        return sesiones.stream()
                .map(sesionmentoriaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SesionmentoriaDTO> paginate(Pageable pageable) {
        Page<Sesionmentoria> sesiones = sesionmentoriaRepository.findAll(pageable);
        return sesiones.map(sesionmentoriaMapper::toDTO);
    }

    @Transactional
    @Override
    public SesionmentoriaDTO create(SesionmentoriaDTO sesionmentoriaDTO) {
        Sesionmentoria sesionmentoria = sesionmentoriaMapper.toEntity(sesionmentoriaDTO);
        Sesionmentoria savedSesion = sesionmentoriaRepository.save(sesionmentoria);
        return sesionmentoriaMapper.toDTO(savedSesion);
    }

    @Transactional
    @Override
    public SesionmentoriaDTO update(Integer id, SesionmentoriaDTO updateSesionmentoriaDTO) {
        Sesionmentoria sesionmentoriaFromDB = sesionmentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La sesion con ID " +id+ " no fue encontrada"));

        if (updateSesionmentoriaDTO.getDia() == null) {
            throw new BadRequestException("El campo dia no puede estar vacío.");
        }

        if (updateSesionmentoriaDTO.getHorainicio().toString().isEmpty()) {
            throw new BadRequestException("La hora de inicio no puede estar vacía.");
        }

        if (updateSesionmentoriaDTO.getHorafinal().toString().isEmpty()) {
            throw new BadRequestException("La hora final no puede estar vacía.");
        }

        if (updateSesionmentoriaDTO.getHorainicio().isAfter(updateSesionmentoriaDTO.getHorafinal())) {
            throw new BadRequestException("La hora de inicio debe ser anterior a la hora final.");
        }

        if (updateSesionmentoriaDTO.getWeblink() == null || updateSesionmentoriaDTO.getWeblink().isEmpty()) {
            throw new BadRequestException("El campo weblink no puede estar vacío.");
        }

        sesionmentoriaFromDB.setDia(updateSesionmentoriaDTO.getDia());
        sesionmentoriaFromDB.setHorainicio(updateSesionmentoriaDTO.getHorainicio());
        sesionmentoriaFromDB.setHorafinal(updateSesionmentoriaDTO.getHorafinal());
        sesionmentoriaFromDB.setWeblink(updateSesionmentoriaDTO.getWeblink());
        sesionmentoriaFromDB = sesionmentoriaRepository.save(sesionmentoriaFromDB);
        return sesionmentoriaMapper.toDTO(sesionmentoriaFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public SesionmentoriaDTO findById(Integer id) {
        Sesionmentoria sesionmentoria = sesionmentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesion no encontrada"));
        return sesionmentoriaMapper.toDTO(sesionmentoria);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Sesionmentoria sesionmentoria = sesionmentoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesion no encontrada"));
        sesionmentoriaRepository.delete(sesionmentoria);
    }
}

package com.reservamentor.service.impl;

import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.exception.BadRequestException;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.mapper.MentorMapper;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.DisponibilidadRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.AdminMentorService;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class AdminMentorServiceImpl implements AdminMentorService {
  @Autowired private MentorRepository mentorRepository;

  @Autowired private MentorMapper mentorMapper;

  @Autowired private DisponibilidadRepository disponibilidadRepository;

  @Transactional(readOnly = true)
  @Override
  public List<InformacionMentorDTO> getAll() {
    List<Mentor> mentors = mentorRepository.findAll();

    return mentors.stream().map(mentorMapper::IMtoDTO).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<InformacionMentorDTO> getAllSortedByRating() {
    List<Mentor> mentors = mentorRepository.findAllOrderByValoracionpromedio();

    return mentors.stream().map(mentorMapper::IMtoDTO).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<InformacionMentorDTO> getMentorsByDia(String dia) {
    if (dia == null || dia.trim().isEmpty()) {
      throw new BadRequestException("El día no puede estar vacío.");
    }

    List<Mentor> mentors = disponibilidadRepository.findMentorsByDia(dia);
    if (mentors.isEmpty()) {
      throw new ResourceNotFoundException(
          "No se encontraron mentores disponibles para el día: " + dia);
    }
    return mentors.stream().map(mentorMapper::IMtoDTO).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<InformacionMentorDTO> getMentorsByHora(LocalTime horaInicio,
                                                     LocalTime horaFin) {
    if (horaInicio.toString().isEmpty() || horaFin.toString().isEmpty()) {
      throw new BadRequestException(
          "Las horas de inicio y fin no pueden estar vacías.");
    }

    if (horaInicio.isAfter(horaFin)) {
      throw new BadRequestException(
          "La hora de inicio debe ser anterior a la hora de fin.");
    }

    List<Mentor> mentors =
        disponibilidadRepository.findMentorsByHora(horaInicio, horaFin);
    if (mentors.isEmpty()) {
      throw new ResourceNotFoundException(
          "No se encontraron mentores disponibles entre las horas: " +
          horaInicio + " y " + horaFin);
    }
    return mentors.stream().map(mentorMapper::IMtoDTO).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<InformacionMentorDTO>
  getMentorsByDiaAndHora(String dia, LocalTime horaInicio, LocalTime horaFin) {

    if (dia == null || dia.trim().isEmpty()) {
      throw new BadRequestException("El día no puede estar vacío.");
    }

    if (horaInicio.toString().isEmpty() || horaFin.toString().isEmpty()) {
      throw new BadRequestException(
          "Las horas de inicio y fin no pueden estar vacías.");
    }

    if (horaInicio.isAfter(horaFin)) {
      throw new BadRequestException(
          "La hora de inicio debe ser anterior a la hora de fin.");
    }

    List<Mentor> mentors = disponibilidadRepository.findMentorsByDiaAndHora(
        dia, horaInicio, horaFin);
    if (mentors.isEmpty()) {
      throw new ResourceNotFoundException(
          "No se encontraron mentores disponibles para el día: " + dia +
          " entre las horas: " + horaInicio + " y " + horaFin);
    }

    return mentors.stream().map(mentorMapper::IMtoDTO).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public Page<InformacionMentorDTO> paginate(Pageable pageable) {
    Page<Mentor> mentors = mentorRepository.findAll(pageable);
    return mentors.map(mentorMapper::IMtoDTO);
  }

  @Transactional
  @Override
  public InformacionMentorDTO
  create(InformacionMentorDTO informacionMentorDTO) {
    Mentor mentor = mentorMapper.toEntity(informacionMentorDTO);
    mentor = mentorRepository.save(mentor);
    return mentorMapper.IMtoDTO(mentor);
  }

  @Transactional
  @Override
  public InformacionMentorDTO
  update(Integer id, InformacionMentorDTO updateInformacionMentorDTO) {
    Mentor mentorFromDb = mentorRepository.findById(id).orElseThrow(
        ()
            -> new RuntimeException("El mentor con ID " + id +
                                    " no fue encontrado"));

    mentorFromDb.setBiografia(updateInformacionMentorDTO.getBiografia());
    mentorFromDb.setTarifahora(updateInformacionMentorDTO.getTarifahora());

    mentorFromDb = mentorRepository.save(mentorFromDb);
    return mentorMapper.IMtoDTO(mentorFromDb);
  }

  @Transactional(readOnly = true)
  @Override
  public InformacionMentorDTO findById(Integer id) {
    Mentor mentor = mentorRepository.findById(id).orElseThrow(
        ()
            -> new RuntimeException("El mentor con ID " + id +
                                    " no fue encontrado"));
    return mentorMapper.IMtoDTO(mentor);
  }

  @Transactional
  @Override
  public void delete(Integer id) {
    Mentor mentorFromDb = mentorRepository.findById(id).orElseThrow(
        ()
            -> new RuntimeException("El mentor con ID " + id +
                                    " no fue encontrado"));
    mentorRepository.delete(mentorFromDb);
  }
}
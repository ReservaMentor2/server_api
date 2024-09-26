package com.reservamentor.service;

import com.reservamentor.dto.InformacionMentorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;

public interface AdminMentorService {
    List<InformacionMentorDTO> getAll();
    Page<InformacionMentorDTO> paginate(Pageable pageable);
    InformacionMentorDTO create(InformacionMentorDTO informacionMentorDTO);
    InformacionMentorDTO update(Integer id, InformacionMentorDTO informacionMentorDTO);
    void delete(Integer id);
    InformacionMentorDTO findById(Integer id);
    List<InformacionMentorDTO> getAllSortedByRating();
    List<InformacionMentorDTO> getMentorsByDia(String dia);
    List<InformacionMentorDTO> getMentorsByHora(LocalTime horaInicio, LocalTime horaFin);
    List<InformacionMentorDTO> getMentorsByDiaAndHora(String dia, LocalTime horaInicio, LocalTime horaFin);
}